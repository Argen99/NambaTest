package com.example.nambatest.ui.fragment.weather

import android.view.View.OnClickListener
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nambatest.R
import com.example.nambatest.core.base.BaseFragment
import com.example.nambatest.core.extensions.showToast
import com.example.nambatest.databinding.FragmentWeatherBinding
import com.example.nambatest.ui.adapter.WeatherInfoPagerAdapter
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WeatherFragment :
    BaseFragment<FragmentWeatherBinding, WeatherViewModel>(R.layout.fragment_weather) {

    override val binding by viewBinding(FragmentWeatherBinding::bind)
    override val viewModel by activityViewModel<WeatherViewModel>()

    private var pagerAdapter: WeatherInfoPagerAdapter? = null

    override fun initialize() {
        pagerAdapter = WeatherInfoPagerAdapter(this)
        binding.vpWeather.apply {
            adapter = pagerAdapter
        }
        binding.dotsIndicator.attachTo(binding.vpWeather)
    }

    override fun setupListeners() {
        binding.vpWeather.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val cityName = pagerAdapter?.getCityNameByPos(position)
                binding.tvCurrentCityName.text = cityName
            }
        })

        val clickAddCity = OnClickListener {
            if (isOnline(requireContext()))
                findNavController().navigate(R.id.action_WeatherFragment_to_SearchCityFragment)
            else
                showToast(getString(R.string.msg_no_internet_connection))
        }
        binding.btnAddCity.setOnClickListener(clickAddCity)
        binding.imgBtnAddCity.setOnClickListener(clickAddCity)

    }

    override fun establishRequest() {
        if (isOnline(requireContext()))
            viewModel.getWeatherData()
        else
            viewModel.getLocalWeatherData()
    }

    override fun launchObservers() {
        viewModel.weatherState.spectateUiState(
            progressBar = binding.progressBar,
            success = { list ->
                pagerAdapter?.add(list.map { it.name })
                binding.containerIsEmpty.isVisible = list.isEmpty()
            },
            error = {
                showToast(it)
            }
        )
    }
}