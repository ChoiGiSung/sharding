package com.coco.sharding.datasource

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import javax.sql.DataSource

@Configuration
@ConfigurationPropertiesScan
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

    // 데이터소스를 생성하는 메서드 구현
    fun dataSource(username: String, password: String, url: String): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.username = username
        dataSource.password = password
        dataSource.url = url
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver")
        return dataSource
    }

    companion object {
        const val SHARD_DELIMITER = "-"
        const val MASTER = "master"
        const val SLAVE = "slave"
    }

}