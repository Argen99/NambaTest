package com.example.data.local.room.entity

import com.example.data.local.room.utils.EntityMapper
import com.example.data.model.Coordinates

data class CoordinatesEntity(
    val lon: Double,
    val lat: Double,
): EntityMapper<Coordinates> {

    override fun toModel() = Coordinates(
        lon = lon,
        lat = lat
    )
}