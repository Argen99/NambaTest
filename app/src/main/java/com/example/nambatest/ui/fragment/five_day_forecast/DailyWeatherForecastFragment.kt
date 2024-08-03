package com.example.nambatest.ui.fragment.five_day_forecast

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.navigateUp
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nambatest.R
import com.example.nambatest.core.base.BaseFragment
import com.example.nambatest.core.extensions.showToast
import com.example.nambatest.databinding.FragmentDailyWeatherForecastBinding
import com.example.nambatest.ui.adapter.ForecastAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DailyWeatherForecastFragment :
    BaseFragment<FragmentDailyWeatherForecastBinding, DailyWeatherForecastViewModel>(R.layout.fragment_daily_weather_forecast) {

    override val binding by viewBinding(FragmentDailyWeatherForecastBinding::bind)
    override val viewModel by viewModel<DailyWeatherForecastViewModel>()
    private val navArgs by navArgs<DailyWeatherForecastFragmentArgs>()
    private val forecastAdapter by lazy {
        ForecastAdapter()
    }

    override fun initialize() {
        binding.rvForecast.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = forecastAdapter
        }
        binding.tvCityName.text = navArgs.cityName
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun establishRequest() {
        viewModel.getFiveDayForecast(navArgs.coord)
    }

    override fun launchObservers() {
        viewModel.forecastState.spectateUiState(
            progressBar = binding.progressBar,
            success = {
                forecastAdapter.submitList(it.dropLast(2))
            },
            error = {
                showToast(it, Toast.LENGTH_LONG)
            }
        )
    }
}