package com.example.final_weather_app.api

import com.example.final_weather_app.model.Weather
import com.example.final_weather_app.utils.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repoimp {
    val weather_api:ApiService
    init {
        var retrofit=Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weather_api=retrofit.create(ApiService::class.java)

    }
    suspend fun getWeather(city: String): Response<Weather>{
        return weather_api.getWeather(city)
    }
}