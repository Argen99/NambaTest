package com.example.nambatest.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.nambatest.ui.fragment.weather.WeatherViewModel
import com.example.nambatest.ui.fragment.search_city.SearchCityViewModel
import com.example.nambatest.ui.fragment.five_day_forecast.DailyWeatherForecastViewModel

val appModule = module {
    viewModelOf(::WeatherViewModel)
    viewModelOf(::SearchCityViewModel)
    viewModelOf(::DailyWeatherForecastViewModel)
}