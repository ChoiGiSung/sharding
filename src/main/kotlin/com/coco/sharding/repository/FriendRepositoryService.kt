package com.coco.sharding.repository

import com.coco.sharding.Sharding
import com.coco.sharding.sharding.ShardingTarget
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, readOnly = true)
@Sharding(target = ShardingTarget.FRIEND)
class FriendRepositoryService {

    // first parameter is sharding key
    @Transactional(isolation = Isolation.READ_COMMITTED)
    fun save(userId: Long) {

    }

    fun findByUserId(userId: Long) {

    }
}