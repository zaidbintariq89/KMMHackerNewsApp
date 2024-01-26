package com.mobilelive.looking4app

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.mobilelive.looking4app.ui.login.LoginScreen

@Composable
fun App() {
    // For Login Screen
    Navigator(LoginScreen())
}
