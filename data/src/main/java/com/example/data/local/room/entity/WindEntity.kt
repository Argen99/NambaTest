package com.example.data.local.room.entity

import com.example.data.local.room.utils.EntityMapper
import com.example.data.model.Wind

data class WindEntity(
    val speed: Double
): EntityMapper<Wind> {
    override fun toModel() = Wind(
        speed = speed
    )
}