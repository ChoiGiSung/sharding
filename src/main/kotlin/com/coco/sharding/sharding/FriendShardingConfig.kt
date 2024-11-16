package com.coco.sharding.sharding

import com.coco.sharding.sharding.ShardingConfig
import com.coco.sharding.sharding.ShardingProperty
import com.coco.sharding.sharding.ShardingTarget
import jakarta.annotation.PostConstruct
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "sharding")
class FriendShardingConfig(
    private val friend: ShardingProperty
) {

    @PostConstruct
    fun init() {
        ShardingConfig.shardingPropertyMap[ShardingTarget.FRIEND] = friend
    }
}