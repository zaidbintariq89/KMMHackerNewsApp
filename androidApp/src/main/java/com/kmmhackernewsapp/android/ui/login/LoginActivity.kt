package com.kmmhackernewsapp.android.ui.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.kmmhackernewsapp.android.MyApplicationTheme

class LoginActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginView(loginViewModel)
                }
            }
        }
    }
}

@Composable
fun LoginView(loginViewModel: LoginViewModel) {
    LaunchedEffect(Unit) {
        loginViewModel.signIn("zaid.tariq@mobilelive.ca", "testing123")
    }
}