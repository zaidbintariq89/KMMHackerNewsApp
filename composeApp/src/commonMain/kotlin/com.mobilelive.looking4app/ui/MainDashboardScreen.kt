package com.mobilelive.looking4app.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.mobilelive.looking4app.ui.tabs.HomeTab
import com.mobilelive.looking4app.ui.tabs.ProfileTab
import com.mobilelive.looking4app.ui.tabs.SettingsTab

class MainDashboardScreen: Screen {
    @Composable
    override fun Content() {
        // For Screen Tabs
        TabNavigator(
            tab = HomeTab
        ) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomNavigation(
                        backgroundColor = Color(207,0,0),
                        contentColor = Color.White,

                    ) {
                        TabNavigationItem(HomeTab)
                        TabNavigationItem(ProfileTab)
                        TabNavigationItem(SettingsTab)
                    }
                },
                content = { CurrentTab() },
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator: TabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            selected = tabNavigator.current == tab,
            onClick = { tabNavigator.current = tab },
            icon = {
                tab.options.icon?.let { icon ->
                    Icon(
                        painter = icon,
                        contentDescription =
                        tab.options.title
                    )
                }
            },
            label = {
                Text(
                    color = Color.White,
                    text = tab.options.title
                )
            }
        )
    }
}