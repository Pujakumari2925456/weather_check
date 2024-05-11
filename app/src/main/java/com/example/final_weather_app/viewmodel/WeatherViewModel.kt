package com.example.final_weather_app.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_weather_app.api.Repoimp
import com.example.final_weather_app.model.Weather

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class WeatherViewModel():ViewModel(){

    private val  _temperature=MutableStateFlow("")
    val temperature=_temperature.asStateFlow()

    private val  _wind=MutableStateFlow("")
    val wind=_wind.asStateFlow()

    private val  _description=MutableStateFlow("")
    val description=_description.asStateFlow()

    private val  _forecast= MutableStateFlow<List<Weather>>(emptyList())
    val forecast=_forecast.asStateFlow()

    private val _inputCity = MutableStateFlow("")
    val inputCity: StateFlow<String> = _inputCity
    fun onInputCityChanged(city:String){
        _inputCity.value = city
    }
    fun OnsearchClicked(){
        viewModelScope.launch {
            try {
               val apiService=Repoimp().getWeather(inputCity.value)

                val temperature=apiService.body()?.temperature!!
                val wind=apiService.body()?.wind!!
                val description=apiService.body()?.description!!
                val forecast=listOf(apiService.body()?.forecast())

                _temperature.value=temperature
                _wind.value=wind
                _description.value=description
                _forecast.value= forecast


            }
            catch (e:Exception){
                Log.e("weatherViewModel","Error",e)
            }
        }
    }
}

