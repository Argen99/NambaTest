package com.example.nambatest.ui.fragment.search_city

import androidx.lifecycle.viewModelScope
import com.example.data.model.City
import com.example.data.model.WeatherData
import com.example.data.repository.WeatherRepository
import com.example.nambatest.core.base.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchCityViewModel(
    private val repository: WeatherRepository
): BaseViewModel() {

    private val _citiesState = mutableUiStateFlow<List<City>>()
    val citiesState = _citiesState.asStateFlow()

    private val _weatherState = mutableUiStateFlow<WeatherData>()
    val weatherState = _weatherState.asStateFlow()

    fun searchCity(query: String) {
        repository.searchCities(query).gatherRequest(_citiesState)
    }

    fun getWeather(lat: Double, lon: Double) {
        repository.getWeather(lat = lat, lon = lon).gatherRequest(_weatherState)
    }

    fun insertData(weatherData: WeatherData) {
        viewModelScope.launch {
            repository.insertToRoom(weatherData)
        }
    }
}