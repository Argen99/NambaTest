package com.example.data.local.room.entity

import com.example.data.local.room.utils.EntityMapper
import com.example.data.model.MainWeatherData

data class MainWeatherDataEntity(
    val temp: Double,
    val humidity: Int,
): EntityMapper<MainWeatherData> {
    override fun toModel() = MainWeatherData(
        temp = temp,
        humidity = humidity,
        temp_min = null,
        temp_max = null
    )
}