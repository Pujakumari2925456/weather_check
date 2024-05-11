package com.example.final_weather_app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.final_weather_app.ui.theme.Final_weather_appTheme
import com.example.final_weather_app.viewmodel.WeatherViewModel


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             val viewModel by viewModels<WeatherViewModel>()

            Final_weather_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}
@Composable
fun WeatherScreen(viewModel: WeatherViewModel= androidx.lifecycle.viewmodel.compose.viewModel()) {
    var inputCity by remember { mutableStateOf("") }
    val temperature by remember{mutableStateOf("")}
    var wind by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var forecast by remember { mutableStateOf("") }
    Row {
        OutlinedTextField(value = inputCity,
        onValueChange = { viewModel.onInputCityChanged(it) },
        label = { Text("Enter city:") },
    var inputCity by viewModel.inputCity.collectAsState()
    }
    val temperature by viewModel.temperature.collectAsState()
    val wind by viewModel.wind.collectAsState()
    val description by viewModel.description.collectAsState()
    val forecast by viewModel.forecast.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

    ) {
        // Temperature display
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Temperature: $temperature",
            fontSize = 15.sp,


        )

        // Wind speed display
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Wind Speed: $wind",
            fontSize = 16.sp,


        )

        // Weather description display
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Description: $description",
            fontSize = 17.sp,


        )

        // Forecast display
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Forecast: ${forecast.joinToString(", ") { it.toString() }}",
            fontSize = 18.sp,


        )

        // Search button
        Button(onClick = { viewModel. OnsearchClicked()}) {
            Text(text = "Search")
        }
    }
}

