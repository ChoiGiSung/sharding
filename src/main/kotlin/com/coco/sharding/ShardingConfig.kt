package com.coco.sharding

import java.util.concurrent.ConcurrentHashMap

class ShardingConfig {
    companion object {
        val shardingPropertyMap = ConcurrentHashMap<ShardingTarget, ShardingProperty>()
    }
}