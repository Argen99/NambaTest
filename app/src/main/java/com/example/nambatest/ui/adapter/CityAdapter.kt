package com.example.nambatest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.data.model.City
import com.example.data.model.Coordinates
import com.example.nambatest.R
import com.example.nambatest.databinding.ItemCityBinding

class CityAdapter(
    private val onItemClick: (lat: Double, lon: Double) -> Unit
) : ListAdapter<City, CityAdapter.CityViewHolder>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CityViewHolder(
        ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        getItem(position)?.let { city -> holder.onBind(city) }
    }

    inner class CityViewHolder(private val binding: ItemCityBinding) : ViewHolder(binding.root) {

        fun onBind(city: City) {
            binding.tvCity.text = city.localNames?.ru ?: city.name
        }

        init {
            binding.root.setOnClickListener {
                val item = getItem(adapterPosition)
                onItemClick(item.lat, item.lon)
            }
            binding.btnAddCity.setOnClickListener {
                val item = getItem(adapterPosition)
                onItemClick(item.lat, item.lon)
            }
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<City>() {
            override fun areItemsTheSame(oldItem: City, newItem: City) =
                oldItem.hashCode() == newItem.hashCode()

            override fun areContentsTheSame(oldItem: City, newItem: City) =
                oldItem == newItem
        }
    }
}