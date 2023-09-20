package com.kmmhackernewsapp.android.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmmhackernewsapp.android.MyApplicationTheme
import com.kmmhackernewsapp.android.R
import com.kmmhackernewsapp.android.ui.rocket.MainActivity

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
                    if (loginViewModel.checkIfUserLogin()) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        LoginView(::onLoginCLicked)
                    }
                }
            }
        }
    }

    private fun onLoginCLicked(username: String, password: String) {
        loginViewModel.signIn(username, password)
        loginViewModel.loginObserver.observe(this) { response ->
            response?.let {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } ?: kotlin.run {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun LoginView(onLoginClicked: (username: String, password: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        // Username and password fields...
        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text(text = stringResource(R.string.username_label)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = stringResource(R.string.password_label)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onLoginClicked(username.value, password.value)
                }
            )
        )
        Button(
            onClick = {
                onLoginClicked(username.value, password.value)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        LoginView(onLoginClicked = { _, _ -> /* Handle login button click */ })
    }
}