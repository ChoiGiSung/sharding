package com.coco.sharding

import com.coco.sharding.model.Friend
import org.springframework.web.bind.annotation.*

@RestController
class Controller(
    private val friendService: FriendService
) {

    @GetMapping("/{userId}")
    fun findByUserId(
        @PathVariable userId: Long
    ): List<Friend> {
        return friendService.findByUserId(userId)
    }

    @PostMapping
    fun save(
        @RequestBody request: Request
    ): Friend {
        return friendService.save(request.userId)
    }

    class Request(
        val userId: Long
    )
}
