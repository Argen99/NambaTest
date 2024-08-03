package com.example.data.local.room.entity

import com.example.data.local.room.utils.EntityMapper
import com.example.data.model.Weather

data class WeatherEntity(
    val description: String,
    val icon: String
): EntityMapper<Weather> {
    override fun toModel() = Weather(
        description = description,
        icon = icon
    )
}