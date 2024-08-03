package com.example.data.model

data class DailyWeather(
    val dt: Long,
    val temp: Temp,
    val humidity: Int,
    val weather: List<Weather>,
)