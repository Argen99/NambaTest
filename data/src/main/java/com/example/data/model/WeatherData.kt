package com.example.data.model

import com.example.data.local.room.entity.WeatherDataEntity

data class WeatherData(
    val id: Long,
    val coord: Coordinates,
    val weather: List<Weather>,
    val main: MainWeatherData,
    val wind: Wind,
    val dt: Long,
    val name: String
) {
    fun toEntity() = WeatherDataEntity(
        id = id,
        coord = coord.toEntity(),
        weather = weather.first().toEntity(),
        main = main.toEntity(),
        wind = wind.toEntity(),
        dt = dt,
        name = name,
    )
}