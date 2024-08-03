package com.example.data.remote.api_service

import com.example.data.model.City
import com.example.data.model.WeatherData
import com.example.data.model.DailyWeatherForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET(GET_CURRENT_WEATHER_END_POINT)
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = KEY,
        @Query("units") units: String = UNIT,
        @Query("lang") lang: String = LANG,
    ): WeatherData

    @GET(SEARCH_CITIES_END_POINT)
    suspend fun searchCities(
        @Query("q") city: String,
        @Query("limit") limit: Int = Int.MAX_VALUE,
        @Query("appid") id: String = KEY,
        @Query("lang") lang: String = LANG,
    ) : List<City>

    @GET(GET_WEATHER_DATA_END_POINT)
    suspend fun getDailyWeatherData(
        @Query("lon") lon: Double,
        @Query("lat") lat: Double,
        @Query("appid") id: String = KEY,
        @Query("units") units: String = UNIT,
        @Query("lang") lang: String = LANG,
        @Query("exclude") exclude: String = ONLY_DAILY,
    ) : DailyWeatherForecastResponse

    companion object {
        const val GET_CURRENT_WEATHER_END_POINT = "data/2.5/weather"
        const val SEARCH_CITIES_END_POINT = "geo/1.0/direct"
        const val KEY = "5b49f8456f02d6d98613edc89de608ec"
        const val UNIT = "metric"
        const val LANG = "ru"
        const val GET_WEATHER_DATA_END_POINT = "data/3.0/onecall"
        const val ONLY_DAILY = "hourly,minutely,alerts,current"
    }
}