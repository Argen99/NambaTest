package com.example.data.model

import com.google.gson.annotations.SerializedName

data class City(
    val name: String,
    @SerializedName("local_names")
    val localNames: LocalCityNames?,
    val lat: Double,
    val lon: Double,
)
