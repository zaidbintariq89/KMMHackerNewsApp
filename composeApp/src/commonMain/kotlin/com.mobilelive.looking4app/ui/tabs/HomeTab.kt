package com.mobilelive.looking4app.ui.tabs

import ServiceRequest
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.mobilelive.looking4app.data.repository.DataState
import com.mobilelive.looking4app.ui.addservice.AddServiceScreen
import com.mobilelive.looking4app.ui.common.ProgressIndicator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

object HomeTab : Tab {
    private val homeViewModel = HomeViewModel()
    @Composable
    override fun Content() {

        LaunchedEffect(this) {
            homeViewModel.getAllServices()
        }
        homeViewModel.serviceResponse.collectAsState().value.let { response ->
            when (response) {
                is DataState.Loading -> {
                    ProgressIndicator()
                }

                is DataState.Success -> {
                    println("Services::: Success :: ${response.data}")
                    showListOfServices(response.data)
                }

                is DataState.Error -> {
                    println(response.exception)
                }
                else -> {}
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun showListOfServices(data: List<ServiceRequest>) {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Home",
                            maxLines = 1,
                        )
                    }
                )
            },
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxSize().padding(top = 60.dp),
                content = {
                    items(data.size) { index ->
                        ProductItem(data[index])
                    }
                }
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                containerColor = Color.Red,
                contentColor = Color.White,
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 80.dp)
                    .align(alignment = Alignment.BottomEnd),
                onClick = {
                    navigator.push(AddServiceScreen)
                }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
//        LazyColumn {
//            items(data) {
//                ServiceListItem(it)
//            }
//        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun ProductItem(data: ServiceRequest) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(
                    onClick = {
                        /*onItemClicked(dog)*/
                    },
                ),
            shape = MaterialTheme.shapes.medium,
        ) {

            Column(modifier = Modifier.height(280.dp).padding(10.dp)) {
                Icon(
                    painter = painterResource("compose-multiplatform.xml"),//id = DrawableResourceIcon(MyIcons.AppIcon).id
                    tint = Color(207,0,0),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = data.name,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = data.description,
                        fontWeight = FontWeight.Normal,
                        maxLines = 4,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }

    @Composable
    fun ServiceListItem(data: ServiceRequest) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(
                        onClick = {
                            /*onItemClicked(dog)*/
                        },
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            text = data.name,
                            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                            fontWeight = FontWeight.Bold,
                            style = typography.subtitle1
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = data.description,
                            modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                            style = typography.caption
                        )

                    }
                }
            }
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            val title = "Home"
            val icon = rememberVectorPainter(Icons.Default.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

}