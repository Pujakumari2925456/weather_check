package com.example.final_weather_app.api

import com.example.final_weather_app.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather/Launda")
    suspend fun getWeather(
        @Query("q") city: String,
    ):Response<Weather>
}