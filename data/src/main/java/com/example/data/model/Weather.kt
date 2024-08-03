package com.example.data.model

import com.example.data.local.room.entity.WeatherEntity

data class Weather(
    val description: String,
    val icon: String
) {
    fun toEntity() = WeatherEntity(
        description = description,
        icon = icon
    )
}