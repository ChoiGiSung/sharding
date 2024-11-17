package com.coco.sharding.model

import jakarta.persistence.*


@Entity
class Friend(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "user_id")
    val userId: Long
) {

}