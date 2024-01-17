package com.mobilelive.looking4app.ui.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.mobilelive.looking4app.ui.dashboard.tabs.HomeTab
import com.mobilelive.looking4app.ui.dashboard.tabs.ProfileTab


class MainDashBoardScreen : Screen {
    @Composable
    override fun Content() {
        mainDashBoardContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainDashBoardContent() {
    TabNavigator(
        HomeTab,
        tabDisposable = {
            TabDisposable(
                navigator = it,
                tabs = listOf(HomeTab, ProfileTab)
            )
        }
    ) { tabNavigator ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = tabNavigator.current.options.title) }
                )
            },
            content = {
                CurrentTab()
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = { tabNavigator.current = HomeTab }) {
                            Icon(Icons.Filled.Check, contentDescription = "Localized description")
                        }
                        IconButton(onClick = { tabNavigator.current = ProfileTab }) {
                            Icon(
                                Icons.Filled.Edit,
                                contentDescription = "Localized description",
                            )
                        }
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                Icons.Filled.Mic,
                                contentDescription = "Localized description",
                            )
                        }
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                Icons.Filled.Image,
                                contentDescription = "Localized description",
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { /* do something */ },
                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, "Localized description")
                        }
                    }
                )
            }
        )
    }
}