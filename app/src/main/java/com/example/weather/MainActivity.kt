package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTheme {
                WeatherApp()
            }
        }
    }
}

@Composable
fun WeatherApp(modifier: Modifier = Modifier) {
    var showSplashScreen by remember { mutableStateOf(true) }
    var showNavigationDialog by remember { mutableStateOf(false) }

    if (showSplashScreen) {
        SplashScreen {
            showSplashScreen = false
            showNavigationDialog = true
        }
    } else {
        MainContent()
    }

    if (showNavigationDialog) {
        NavigationDialog(
            onNavigateToMainScreen = {
                showNavigationDialog = false
            },
            onExitApp = {
                showNavigationDialog = false
            }
        )
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(7000)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "WEATHER",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00E5FF)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.clouds_3488632_1280 ),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "LATHANE KAMOGELO",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00E5FF)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "ST10273510",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00E5FF)
            )
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("detailed") { DetailedViewScreen(navController) }
    }
}

data class WeatherData(
    val day: String,
    var morningTemp: String = "",
    var afternoonTemp: String = "",
    var weatherCondition: String = ""
)

@Composable
fun MainScreen(navController: NavHostController) {
    var weatherData by remember { mutableStateOf(
        listOf(
            WeatherData("Monday"),
            WeatherData("Tuesday"),
            WeatherData("Wednesday"),
            WeatherData("Thursday"),
            WeatherData("Friday"),
            WeatherData("Saturday"),
            WeatherData("Sunday")
        )
    ) }
    val listState = rememberLazyListState()

    fun calculateAverageTemp(): Double {
        var totalTemp = 0.0
        var count = 0
        for (record in weatherData) {
            val morningTemp = record.morningTemp.toDoubleOrNull() ?: 0.0
            val afternoonTemp = record.afternoonTemp.toDoubleOrNull() ?: 0.0
            totalTemp += morningTemp + afternoonTemp
            count += if (morningTemp > 0) 1 else 0
            count += if (afternoonTemp > 0) 1 else 0
        }
        return if (count > 0) totalTemp / count else 0.0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f)
        ) {
            items(weatherData.size) { index ->
                val record = weatherData[index]
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = record.day)
                    TextField(
                        value = record.morningTemp,
                        onValueChange = { newTemp ->
                            weatherData = weatherData.toMutableList().also {
                                it[index] = it[index].copy(morningTemp = newTemp)
                            }
                        },
                        label = { Text("Morning Temperature") }
                    )
                    TextField(
                        value = record.afternoonTemp,
                        onValueChange = { newTemp ->
                            weatherData = weatherData.toMutableList().also {
                                it[index] = it[index].copy(afternoonTemp = newTemp)
                            }
                        },
                        label = { Text("Afternoon Temperature") }
                    )
                    TextField(
                        value = record.weatherCondition,
                        onValueChange = { newCondition ->
                            weatherData = weatherData.toMutableList().also {
                                it[index] = it[index].copy(weatherCondition = newCondition)
                            }
                        },
                        label = { Text("Weather Condition") }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Average Temperature: ${calculateAverageTemp()}°C")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { weatherData = List(7) { WeatherData(it.toString()) } }) {
            Text(text = "Clear Data")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("detailed") }) {
            Text(text = "Detailed View")
        }
        val itemList = remember { mutableListOf<String>() }

    }
}

@Composable
fun DetailedViewScreen(navController: NavHostController) {
    var weatherData by remember { mutableStateOf(
        listOf(
            WeatherData("Monday"),
            WeatherData("Tuesday"),
            WeatherData("Wednesday"),
            WeatherData("Thursday"),
            WeatherData("Friday"),
            WeatherData("Saturday"),
            WeatherData("Sunday")
        )
    ) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(weatherData.size) { index ->
                val record = weatherData[index]
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = record.day,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Morning Temperature: ${record.morningTemp}°C",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Afternoon Temperature: ${record.afternoonTemp}°C",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Weather Condition: ${record.weatherCondition}",
                        fontSize = 16.sp
                    )
                }

                Button(
                    onClick = { navController.navigate("main") },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Back")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}

@Composable
fun NavigationDialog(
    onNavigateToMainScreen: () -> Unit,
    onExitApp: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Navigate") },
        text = { Text(text = "Do you want to navigate to the main screen or exit the app?") },
        confirmButton = {
            Button(onClick = onNavigateToMainScreen) {
                Text("Main Screen")
            }
        },
        dismissButton = {
            Button(onClick = onExitApp) {
                Text("Exit App")
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    WeatherTheme {
        WeatherApp()
    }
}