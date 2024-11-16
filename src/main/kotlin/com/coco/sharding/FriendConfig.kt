package com.coco.sharding

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import javax.sql.DataSource

@Configuration
@ConfigurationProperties(prefix = "datasource")
class FriendConfig(
    private val friend: ShardingDataSourceProperty
) {

    @Bean
    fun friendDataSource(): LazyConnectionDataSourceProxy {
        val router: DataSourceRouter = DataSourceRouter()
        val dataSourceMap = linkedMapOf<Any, Any>()

        for ((index, shard) in friend.shards.withIndex()) {
            val masterDs = dataSource(shard.username, shard.password, shard.master.url)
            dataSourceMap["$index$SHARD_DELIMITER${shard.master.name}"] = masterDs

            for (slave in shard.slaves) {
                dataSourceMap["$index$SHARD_DELIMITER${slave.name}"] = dataSource(shard.username, shard.password, slave.url)
            }
        }
        router.setTargetDataSources(dataSourceMap)
        router.afterPropertiesSet()

        return LazyConnectionDataSourceProxy(router)
    }

    fun dataSource(username: String, password: String, url: String): DataSource {
        TODO("Not yet implemented")
    }

    companion object {
        private const val SHARD_DELIMITER = "-"
    }

}