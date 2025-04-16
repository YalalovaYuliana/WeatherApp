package com.example.weatherapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.ui.theme.WeatherAppTheme
import org.json.JSONObject

const val API_KEY = "c49342488dd54f00ab8165943251604"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Madrid", this)
                }
            }
        }
    }
}

@Composable
fun Greeting(cityName: String, context: Context) {
    val state = remember {
        mutableStateOf("Unknown")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxHeight(.5f)
            .fillMaxWidth()
            .background(Color.Green),
            contentAlignment = Alignment.Center) {
            Text(text = "Temp in $cityName = ${state.value}")
        }
        Box(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Blue),
            contentAlignment = Alignment.BottomCenter) {
            Button(onClick = {
                getResult(cityName, state, context)
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Refresh")
            }
        }
    }

}

private fun getResult(cityName: String, state: MutableState<String>, context: Context) {
    val url = "https://api.weatherapi.com/v1/current.json?key=$API_KEY&q=$cityName&aqi=no"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        {
                response ->
            Log.d("MyLog", "Response: $response")
            val obj = JSONObject(response)
            val temp = obj.getJSONObject("current").getString("temp_c")
            state.value = temp + "C"
        },
        {
                error -> Log.d("MyLog", "Volley error: $error")
        })

    queue.add(stringRequest)
}

