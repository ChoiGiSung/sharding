package com.coco.sharding.repository

import com.coco.sharding.Sharding
import com.coco.sharding.model.Friend
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
class FriendRepositoryService(
    private val friendRepository: FriendRepository
) {

    // first parameter is sharding key
    @Transactional(isolation = Isolation.READ_COMMITTED)
    fun save(userId: Long): Friend {
        return friendRepository.save(Friend(userId = userId))
    }

    fun findByUserId(userId: Long): List<Friend> {
        return friendRepository.findByUserId(userId)
    }
}