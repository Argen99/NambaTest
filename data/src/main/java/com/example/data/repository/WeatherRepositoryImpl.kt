package com.example.data.repository

import com.example.data.core.Either
import com.example.data.core.makeNetworkRequest
import com.example.data.local.room.WeatherDao
import com.example.data.model.Coordinates
import com.example.data.model.DailyWeather
import com.example.data.model.WeatherData
import com.example.data.remote.api_service.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherRepositoryImpl(
    private val apiService: WeatherApiService,
    private val weatherDao: WeatherDao
) : WeatherRepository {

    override fun getWeatherList() = flow<Either<List<WeatherData>>> {
        weatherDao.getAllCoordinates().collect { list ->
            val weatherData = mutableListOf<WeatherData>()
            list.forEach { data ->
                val lat = data.coord.lat
                val lon = data.coord.lon
                val response = apiService.getCurrentWeather(lat = lat, lon = lon)
                weatherData.add(response)
            }
            emit(Either.Success(weatherData))
        }
    }.flowOn(Dispatchers.IO).catch {
        it.printStackTrace()
        emit(Either.Error(it.message ?: UNKNOWN_ERROR_MESSAGE))
    }

    override fun getWeather(lat: Double, lon: Double) = makeNetworkRequest {
            apiService.getCurrentWeather(lat = lat, lon = lon)
        }

    override fun searchCities(cityName: String) = makeNetworkRequest {
        apiService.searchCities(cityName)
    }

    override fun getDailyWeatherData(coordinates: Coordinates): Flow<Either<List<DailyWeather>>> = makeNetworkRequest {
        apiService.getDailyWeatherData(
            lat = coordinates.lat,
            lon = coordinates.lon
        ).daily
    }

    override suspend fun insertToRoom(weatherData: WeatherData) {
        weatherDao.insertWeatherData(weatherData.toEntity())
    }

    override fun getLocalWeatherList() = flow<Either<List<WeatherData>>> {
        weatherDao.getWeatherData().collect { list ->
            emit(Either.Success(list.map { it.toWeatherData() }))
        }
    }.flowOn(Dispatchers.IO).catch {
        emit(Either.Error(it.message ?: UNKNOWN_ERROR_MESSAGE))
    }

    companion object {
        const val UNKNOWN_ERROR_MESSAGE = "Error Occurred!"
    }
}