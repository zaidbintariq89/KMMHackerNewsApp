package com.mobilelive.looking4app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import com.mobilelive.looking4app.ui.login.LoginScreen
import com.mobilelive.looking4app.ui.login.loginView
import com.mobilelive.looking4app.ui.register.registerPageContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigator(LoginScreen())
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    registerPageContent(null,null)
}