package com.coco.sharding.sharding

import jakarta.annotation.PostConstruct
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan
class FriendShardingConfig(
    private val friend: ShardingProperty
) {

    @PostConstruct
    fun init() {
        ShardingConfig.shardingPropertyMap[ShardingTarget.FRIEND] = friend
    }
}