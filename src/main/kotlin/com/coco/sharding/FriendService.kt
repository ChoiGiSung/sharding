package com.coco.sharding

import com.coco.sharding.repository.FriendRepositoryService
import org.springframework.stereotype.Service

@Service
class FriendService(
    private val friendRepositoryService: FriendRepositoryService
) {
    fun save(userId: Long) = friendRepositoryService.save(userId)
    fun findByUserId(userId: Long) = friendRepositoryService.findByUserId(userId)
}