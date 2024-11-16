package com.coco.sharding.datasource

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "datasource.friend")
class ShardingDataSourceProperty(
    val shards: List<Shard>
) {

    class Shard(
        val username: String,
        val password: String,
        val master: Property,
        val slaves: List<Property>
    )

    class Property(
        val name: String,
        val url: String
    )
}