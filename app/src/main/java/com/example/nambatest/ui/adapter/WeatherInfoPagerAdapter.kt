package com.example.nambatest.ui.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nambatest.ui.fragment.weather.weather_info.WeatherInfoFragment

@SuppressLint("NotifyDataSetChanged")
class WeatherInfoPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val cityList: MutableList<String> = mutableListOf()

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun createFragment(position: Int): Fragment {
        return WeatherInfoFragment.newInstance(position)
    }

    fun getCityNameByPos(position: Int): String = cityList[position]

    fun add(city: String) {
        cityList.add(city)
        notifyDataSetChanged()
    }

    fun add(cities: List<String>) {
        cityList.clear()
        cityList.addAll(cities)
        notifyDataSetChanged()
    }

    fun remove(index: Int) {
        cityList.removeAt(index)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return cityList[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return cityList.any { it.hashCode().toLong() == itemId }
    }

//    fun remove(name: FragmentName) {
//        cityIds.remove(name)
//        notifyDataSetChanged()
//    }
}