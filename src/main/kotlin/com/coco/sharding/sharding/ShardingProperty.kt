package com.coco.sharding.sharding

import com.coco.sharding.datasource.ShardingStrategy

class ShardingProperty(
    val strategy: ShardingStrategy,
    val rules: List<ShardingRule>,
    val mod: Int
) {

    fun isRangeSharding(): Boolean {
        return strategy == ShardingStrategy.RANGE
    }

    fun isModulusSharding(): Boolean {
        return strategy == ShardingStrategy.MODULAR
    }

    class ShardingRule(
        val shardNo: Int,
        val rangeMin: Long,
        val rangeMax: Long
    )
}