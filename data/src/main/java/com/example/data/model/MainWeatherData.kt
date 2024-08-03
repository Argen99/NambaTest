package com.example.data.model

import com.example.data.local.room.entity.MainWeatherDataEntity
import com.google.gson.annotations.SerializedName

data class MainWeatherData(
    val temp: Double,
    val humidity: Int,
    val temp_min: Double?,
    val temp_max: Double?
) {
    fun toEntity() = MainWeatherDataEntity(
        temp = temp,
        humidity = humidity,
    )
}