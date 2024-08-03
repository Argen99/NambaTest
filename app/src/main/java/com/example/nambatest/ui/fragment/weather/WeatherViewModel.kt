package com.example.nambatest.ui.fragment.weather

import com.example.data.model.WeatherData
import com.example.data.repository.WeatherRepository
import com.example.nambatest.core.base.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow

class WeatherViewModel(
    private val repository: WeatherRepository
): BaseViewModel() {

    private val _weatherState = mutableUiStateFlow<List<WeatherData>>()
    val weatherState = _weatherState.asStateFlow()

    fun getWeatherData() {
        repository.getWeatherList().gatherRequest(_weatherState)
    }

    fun getLocalWeatherData() {
        repository.getLocalWeatherList().gatherRequest(_weatherState)
    }
}