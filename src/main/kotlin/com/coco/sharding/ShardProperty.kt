package com.coco.sharding

class ShardProperty(
    private val strategy: ShardingStrategy,
    private val rules: List<ShardingRule>,
    private val mod: Int
) {

    class ShardingRule(
        private val shardNo: Int,
        private val rangeMin: Long,
        private val rangeMax: Long
    )
}