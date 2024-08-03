package com.example.data.local.room

import com.example.data.local.room.utils.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.room.entity.WeatherDataEntity

@Database(entities = [WeatherDataEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}