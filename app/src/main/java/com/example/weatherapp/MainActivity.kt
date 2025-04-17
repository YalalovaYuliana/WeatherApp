package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.screens.MainCard
import com.example.weatherapp.screens.TabLayout
import com.example.weatherapp.ui.theme.WeatherAppTheme

const val API_KEY = "c49342488dd54f00ab8165943251604"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Image(
                    painter = painterResource(id = R.drawable.weather_background),
                    contentDescription = "Weather background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(.8f)
                )
                Column {
                    MainCard()
                    TabLayout()
                }
            }
        }
    }
}



