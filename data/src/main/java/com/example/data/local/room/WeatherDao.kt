package com.example.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.room.entity.WeatherDataEntity
import com.example.data.local.room.tuple.CoordinatesTuple
import com.example.data.model.Coordinates
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeatherData(weather: WeatherDataEntity)

    @Query("SELECT * FROM weather_table ORDER BY create_date ASC")
    fun getWeatherData(): Flow<List<WeatherDataEntity>>

    @Query("SELECT coord FROM weather_table ORDER BY create_date ASC")
    fun getAllCoordinates(): Flow<List<CoordinatesTuple>>
}