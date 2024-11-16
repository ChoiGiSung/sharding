package com.coco.sharding

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