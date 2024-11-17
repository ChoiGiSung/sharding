package com.coco.sharding

import com.coco.sharding.sharding.ShardingTarget

class UserHolder {

    companion object {
        private val userContext = ThreadLocal<Context>()

        fun setSharding(target: ShardingTarget, shardKey: Long) {
            if (getUserContext() == null) {
                userContext.set(Context(Sharding(target, shardKey)))
            }
            print("")
        }

        fun clearSharding() {
            getUserContext()?.sharding = null
        }

        fun getSharding(): Sharding? {
            return getUserContext()?.sharding
        }

        private fun getUserContext(): Context? {
            return userContext.get()
        }
    }


    class Context(
        var sharding: Sharding?
    ) {


    }

    class Sharding(
        val shardingTarget: ShardingTarget,
        val shardKey: Long
    )
}