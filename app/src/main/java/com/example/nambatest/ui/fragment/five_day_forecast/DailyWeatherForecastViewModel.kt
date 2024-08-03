package com.example.nambatest.ui.fragment.five_day_forecast

import com.example.data.model.Coordinates
import com.example.data.model.DailyWeather
import com.example.data.repository.WeatherRepository
import com.example.nambatest.core.base.BaseViewModel
import kotlinx.coroutines.flow.asStateFlow

class DailyWeatherForecastViewModel(
    private val repository: WeatherRepository
): BaseViewModel() {

    private val _forecastState = mutableUiStateFlow<List<DailyWeather>>()
    val forecastState = _forecastState.asStateFlow()

    fun getFiveDayForecast(coordinates: Coordinates) {
        repository.getDailyWeatherData(coordinates).gatherRequest(_forecastState)
    }
}