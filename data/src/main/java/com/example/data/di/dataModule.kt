package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.room.AppDatabase
import com.example.data.local.room.WeatherDao
import com.example.data.remote.api_service.WeatherApiService
import com.example.data.repository.WeatherRepositoryImpl
import com.example.data.repository.WeatherRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    factoryOf(::provideOkHttpClient)
    factoryOf(::provideApiService)
    factoryOf(::provideRetrofit)
    singleOf(::WeatherRepositoryImpl) {
        bind<WeatherRepository>()
    }
    singleOf(::provideRoomDB)
    singleOf(::provideWeatherDao)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()
}

fun provideApiService(retrofit: Retrofit): WeatherApiService {
    return retrofit.create(WeatherApiService::class.java)
}

fun provideRoomDB(context: Context) : AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "database.db").build()
}

fun provideWeatherDao(roomDB: AppDatabase): WeatherDao {
    return roomDB.weatherDao()
}