package com.example.data.model

import com.example.data.local.room.entity.WindEntity

data class Wind(
    val speed: Double
) {
    fun toEntity() = WindEntity(
        speed = speed
    )
}