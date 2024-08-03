package com.example.nambatest.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.data.model.DailyWeather
import com.example.nambatest.core.Constants
import com.example.nambatest.core.extensions.formatToDateString
import com.example.nambatest.core.extensions.loadImage
import com.example.nambatest.core.extensions.toImageUrl
import com.example.nambatest.core.extensions.toTitleCase
import com.example.nambatest.databinding.ItemForecastBinding

class ForecastAdapter: ListAdapter<DailyWeather, ForecastAdapter.ForecastViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ForecastViewHolder(
        ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        getItem(position)?.let { weatherData -> holder.onBind(weatherData) }
    }

    inner class ForecastViewHolder(private val binding: ItemForecastBinding) : ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(weatherData: DailyWeather): Unit = with(binding) {
            tvDate.text = weatherData.dt.formatToDateString(Constants.MONTH_DAY_FORMAT_PATTERN)
            tvDescription.text = weatherData.weather.first().description.toTitleCase()
            ivIcon.loadImage(weatherData.weather.first().icon.toImageUrl())
            tvMinMaxTemp.apply {
                val tempMax = weatherData.temp.min.toInt()
                val tempMin = weatherData.temp.max.toInt()
                text = "$tempMax°ᶜ/$tempMin°ᶜ"
            }
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<DailyWeather>() {
            override fun areItemsTheSame(oldItem: DailyWeather, newItem: DailyWeather) =
                oldItem.hashCode() == newItem.hashCode()

            override fun areContentsTheSame(oldItem: DailyWeather, newItem: DailyWeather) =
                oldItem == newItem
        }
    }
}