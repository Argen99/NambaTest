package com.example.data.local.room.utils

import androidx.room.TypeConverter
import com.example.data.local.room.entity.CoordinatesEntity
import com.example.data.local.room.entity.MainWeatherDataEntity
import com.example.data.local.room.entity.WeatherEntity
import com.example.data.local.room.entity.WindEntity
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun fromCoordinates(coordinates: CoordinatesEntity): String {
        return Gson().toJson(coordinates)
    }

    @TypeConverter
    fun toCoordinates(coordinatesString: String): CoordinatesEntity {
        return Gson().fromJson(coordinatesString, CoordinatesEntity::class.java)
    }

    @TypeConverter
    fun fromWeatherList(weatherList: WeatherEntity): String {
        return Gson().toJson(weatherList)
    }

    @TypeConverter
    fun toWeatherList(weatherString: String): WeatherEntity {
        return Gson().fromJson(weatherString, WeatherEntity::class.java)
    }

    @TypeConverter
    fun fromMainWeatherData(mainWeatherData: MainWeatherDataEntity): String {
        return Gson().toJson(mainWeatherData)
    }

    @TypeConverter
    fun toMainWeatherData(mainWeatherDataString: String): MainWeatherDataEntity {
        return Gson().fromJson(mainWeatherDataString, MainWeatherDataEntity::class.java)
    }

    @TypeConverter
    fun fromWind(wind: WindEntity): String {
        return Gson().toJson(wind)
    }

    @TypeConverter
    fun toWind(windString: String): WindEntity {
        return Gson().fromJson(windString, WindEntity::class.java)
    }
}