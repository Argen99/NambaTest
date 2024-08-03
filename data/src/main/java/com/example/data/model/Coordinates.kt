package com.example.data.model

import com.example.data.local.room.entity.CoordinatesEntity
import java.io.Serializable

data class Coordinates(
    val lon: Double,
    val lat: Double,
): Serializable {
    fun toEntity() = CoordinatesEntity(
        lon = lon,
        lat = lat,
    )
}