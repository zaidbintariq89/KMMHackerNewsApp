package com.example.kmmtestapp.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.kmmtestapp.Greeting
import com.example.kmmtestapp.kmm.shared.SpaceXSDK
import com.example.kmmtestapp.kmm.shared.cache.DatabaseDriverFactory
import com.example.kmmtestapp.kmm.shared.entity.RocketLaunch
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val spaceXSdk: SpaceXSDK = SpaceXSDK(DatabaseDriverFactory(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView(Greeting().greet(), spaceXSdk)
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String, spaceXSdk: SpaceXSDK? = null) {
    val rocketState = remember {
        mutableStateOf(emptyList<RocketLaunch>())
    }
    val mainScope = rememberCoroutineScope()
    Column(
        // inside this column we are specifying modifier
        // to specify max width and max height
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        // on below line we are specifying horizontal alignment
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(10.dp),
            style = TextStyle(
                color = Color.Black,
                fontSize = TextUnit(value = 20.0F, type = TextUnitType.Sp)
            ),
            fontWeight = FontWeight.Bold
        )

        // API call
        LaunchedEffect(Unit) {
            mainScope.launch {
                kotlin.runCatching {
                    spaceXSdk?.getLaunches(forceReload = false)
                }.onSuccess {
                    Log.d("MainActivity", "Data received = ${it?.toString()}")
                    it?.let { rockets ->
                        rocketState.value = rockets.sortedByDescending { rocket-> rocket.launchYear }
                    }
                }.onFailure {
                    Log.d("MainActivity", "Data fetch error")
                }
            }
        }

        // bind data
        ItemsListView(list = rocketState.value)
    }
}

@Composable
fun ItemsListView(list: List<RocketLaunch>) {
    LazyColumn {
        items(list) { rocket ->
            val details = "${rocket.missionName} | ${rocket.launchYear} | ${rocket.launchSuccess ?: false}"
            Text(details , modifier = Modifier.padding(15.dp))
            Divider()
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
