package com.example.nambatest.ui.fragment.search_city

import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nambatest.R
import com.example.nambatest.core.base.BaseFragment
import com.example.nambatest.core.extensions.showToast
import com.example.nambatest.databinding.FragmentSearchCityBinding
import com.example.nambatest.ui.adapter.CityAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCityFragment :
    BaseFragment<FragmentSearchCityBinding, SearchCityViewModel>(R.layout.fragment_search_city) {

    override val binding by viewBinding(FragmentSearchCityBinding::bind)
    override val viewModel by viewModel<SearchCityViewModel>()
    private val cityAdapter by lazy {
        CityAdapter(::onItemClick)
    }

    override fun initialize() {
        binding.rvCities.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = cityAdapter
        }
    }

    override fun setupListeners() {
        binding.svCity.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty())
                    viewModel.searchCity(newText)
                return true
            }
        })

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun launchObservers() {
        viewModel.citiesState.spectateUiState(
            progressBar = binding.progressBar,
            success = { cityAdapter.submitList(it) },
            error = { showToast(it) }
        )

        viewModel.weatherState.spectateUiState(
            success = {
                viewModel.insertData(it)
                findNavController().navigateUp()
            },
            error = {
                showToast(it)
            }
        )
    }

    private fun onItemClick(lat: Double, lon: Double) {
        viewModel.getWeather(lat = lat, lon = lon)
    }
}