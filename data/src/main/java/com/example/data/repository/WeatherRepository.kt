package com.example.data.repository

import com.example.data.core.Either
import com.example.data.model.City
import com.example.data.model.Coordinates
import com.example.data.model.DailyWeather
import com.example.data.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getWeatherList(): Flow<Either<List<WeatherData>>>
    fun getWeather(lat: Double, lon: Double): Flow<Either<WeatherData>>
    fun searchCities(cityName: String): Flow<Either<List<City>>>
    fun getDailyWeatherData(coordinates: Coordinates): Flow<Either<List<DailyWeather>>>
    suspend fun insertToRoom(weatherData: WeatherData)
    fun getLocalWeatherList(): Flow<Either<List<WeatherData>>>
}