package com.coco.sharding

import com.coco.sharding.sharding.ShardingTarget

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Sharding(
    val target: ShardingTarget,
) {
}