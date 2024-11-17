package com.coco.sharding.datasource

import com.coco.sharding.sharding.UserHolder
import com.coco.sharding.datasource.FriendConfig.Companion.MASTER
import com.coco.sharding.datasource.FriendConfig.Companion.SHARD_DELIMITER
import com.coco.sharding.datasource.FriendConfig.Companion.SLAVE
import com.coco.sharding.sharding.ShardingConfig
import com.coco.sharding.sharding.ShardingProperty
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager
import java.util.concurrent.ConcurrentHashMap

class DataSourceRouter : AbstractRoutingDataSource() {
    private val shards: MutableMap<Int, MhaDataSource> = ConcurrentHashMap()

    override fun setTargetDataSources(targetDataSources: Map<Any, Any>) {
        super.setTargetDataSources(targetDataSources)
        targetDataSources.keys.forEach { item ->
            val dataSourceName = item.toString()
            val shardNoStr = dataSourceName.split(SHARD_DELIMITER)[0].toInt()
            val shard = getShard(shardNoStr)

            when {
                dataSourceName.contains(MASTER) -> shard.masterName = dataSourceName
                dataSourceName.contains(SLAVE) -> shard.slaveName.add(dataSourceName)
            }
        }
    }

    override fun determineCurrentLookupKey(): Any {
        val shardNo = getShardNo(UserHolder.getSharding())
        val dataSource = shards[shardNo] ?: throw RuntimeException("No DataSource found for shardNo $shardNo")

        return if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            dataSource.slaveName.next()
        } else {
            dataSource.masterName
        }
    }

    private fun getShard(shardNo: Int): MhaDataSource {
        return shards.computeIfAbsent(shardNo) {
            MhaDataSource(slaveName = RoundRobin(mutableListOf()))
        }
    }


    private fun getShardNo(sharding: UserHolder.Sharding?): Int {
        if (sharding == null) return DEFAULT_SHARD_NO
        val shardingProperty = ShardingConfig.shardingPropertyMap[sharding.shardingTarget] ?: return DEFAULT_SHARD_NO
        return when {
            shardingProperty.isRangeSharding() -> getShardNoByRange(shardingProperty.rules, sharding.shardKey)
            shardingProperty.isModulusSharding() -> getShardNoByModular(shardingProperty.mod, sharding.shardKey)
            else -> DEFAULT_SHARD_NO
        }
    }

    private fun getShardNoByRange(rules: List<ShardingProperty.ShardingRule>, shardKey: Long): Int {
        return rules.firstOrNull { it.rangeMin <= shardKey && shardKey <= it.rangeMax }?.shardNo ?: 0
    }

    private fun getShardNoByModular(modulus: Int, shardKey: Long): Int {
        return (shardKey % modulus).toInt()
    }

    data class MhaDataSource(
        var slaveName: RoundRobin<String> = RoundRobin(mutableListOf())
    ) {
        lateinit var masterName: String
    }

    companion object {
        const val DEFAULT_SHARD_NO = 0
    }
}