package com.example.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.model.WeatherData

@Entity(tableName = "weather_table")
data class WeatherDataEntity(
    @PrimaryKey
    val id: Long,
    val coord: CoordinatesEntity,
    val weather: WeatherEntity,
    val main: MainWeatherDataEntity,
    val wind: WindEntity,
    val dt: Long,
    val name: String,
    @ColumnInfo(name = "create_date")
    val createDate: Long = System.currentTimeMillis()
) {
    fun toWeatherData() = WeatherData(
        id = id,
        coord = coord.toModel(),
        weather = listOf(weather.toModel()),
        main = main.toModel(),
        wind = wind.toModel(),
        dt = dt,
        name = name,
    )
}