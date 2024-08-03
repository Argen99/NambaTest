package com.example.nambatest.ui.fragment.weather.weather_info

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.data.model.Coordinates
import com.example.data.model.WeatherData
import com.example.nambatest.R
import com.example.nambatest.core.base.BaseFragment
import com.example.nambatest.core.extensions.formatToDateString
import com.example.nambatest.core.extensions.safeNavigation
import com.example.nambatest.core.extensions.showToast
import com.example.nambatest.core.extensions.toTitleCase
import com.example.nambatest.databinding.FragmentWeatherInfoBinding
import com.example.nambatest.ui.fragment.weather.WeatherFragmentDirections
import com.example.nambatest.ui.fragment.weather.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WeatherInfoFragment :
    BaseFragment<FragmentWeatherInfoBinding, WeatherViewModel>(R.layout.fragment_weather_info) {

    override val binding by viewBinding(FragmentWeatherInfoBinding::bind)
    override val viewModel by activityViewModel<WeatherViewModel>()

    private val position: Int by lazy {
        arguments?.getInt(ARG_KEY) ?: 0
    }
    private var currentCoordinates: Coordinates? = null
    private var currentCityName: String? = null

    override fun setupListeners() {
        binding.tvFiveDayForecast.setOnClickListener {
            if (isOnline(requireContext())) {
                currentCoordinates?.let {
                    findNavController().safeNavigation(
                        WeatherFragmentDirections.actionWeatherFragmentToDailyWeatherForecastFragment(
                            it, currentCityName ?: ""
                        )
                    )
                }
            } else
                showToast(getString(R.string.msg_no_internet_connection))
        }
    }

    override fun launchObservers() {
        viewModel.weatherState.spectateUiState(
            success = {
                setWeatherData(it[position])
            },
            error = {
                showToast(it)
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun setWeatherData(weather: WeatherData) {
        currentCoordinates = weather.coord
        currentCityName = weather.name
        binding.tvTemp.text = "${weather.main.temp.toInt()}°ᶜ"
        binding.tvDescription.text = weather.weather.first().description.toTitleCase()
        binding.tvDate.text = weather.dt.formatToDateString()
        binding.includeWind.apply {
            ivIcon.setImageResource(R.drawable.ic_wind)
            tvValue.text = "${weather.wind.speed}m/s"
            tvLabel.text = "Скорость ветра"
        }
        binding.includeHumidity.apply {
            ivIcon.setImageResource(R.drawable.ic_humidity)
            tvValue.text = "${weather.main.humidity}%"
            tvLabel.text = "Влажность"
        }
    }

    companion object {
        private const val ARG_KEY = "position"
        fun newInstance(position: Int): WeatherInfoFragment {
            val fragment = WeatherInfoFragment()
            val args = Bundle()
            args.putInt(ARG_KEY, position)
            fragment.arguments = args
            return fragment
        }
    }
}