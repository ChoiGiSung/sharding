package com.coco.sharding.sharding

import com.coco.sharding.sharding.ShardingProperty
import com.coco.sharding.sharding.ShardingTarget
import java.util.concurrent.ConcurrentHashMap

class ShardingConfig {
    companion object {
        val shardingPropertyMap = ConcurrentHashMap<ShardingTarget, ShardingProperty>()
    }
}