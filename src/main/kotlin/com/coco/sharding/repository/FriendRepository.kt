package com.coco.sharding.repository

import com.coco.sharding.model.Friend
import org.springframework.data.jpa.repository.JpaRepository

interface FriendRepository: JpaRepository<Friend, Long> {
    fun findByUserId(userId: Long): List<Friend>
}