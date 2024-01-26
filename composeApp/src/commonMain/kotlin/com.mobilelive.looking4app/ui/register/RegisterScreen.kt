package com.mobilelive.looking4app.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mobilelive.looking4app.data.repository.DataState
import com.mobilelive.looking4app.ui.common.GradientButton
import com.mobilelive.looking4app.ui.common.ProgressIndicator
import com.mobilelive.looking4app.ui.common.SimpleOutlinedEmailField
import com.mobilelive.looking4app.ui.common.SimpleOutlinedPasswordTextField
import com.mobilelive.looking4app.ui.common.SimpleTextField
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class RegisterScreen : Screen {
    private val registerViewModel = RegisterViewModel()
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        registerPageContent(navigator, registerViewModel)
        registerViewModel.signUpResponse.collectAsState().value.let {
            when (it) {
                is DataState.Loading -> {
                    ProgressIndicator()
                }

                is DataState.Success<Any> -> {
                    println("Register::: Success")
                    navigator.pop()
                }

                is DataState.Error -> {
                    println(it.exception)
                }
                else -> {}
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun registerPageContent(navigator: Navigator?, viewModel: RegisterViewModel?) {
    var fName by remember { mutableStateOf("") }
    var lName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(true) }
    var showLoader by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent,
            )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource("user_reg.png"),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),

                )
            //.........................Spacer
            Spacer(modifier = Modifier.height(20.dp))
            //.........................Text: title
            androidx.compose.material3.Text(
                text = "Create An Account",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(8.dp))
            SimpleTextField(label = "First Name", hint = "First Name", keyboardType = KeyboardType.Text, onValueChange = {
                fName = it
            })
            Spacer(modifier = Modifier.padding(3.dp))
            SimpleTextField(label = "Last Name", hint = "Last Name", keyboardType = KeyboardType.Text, onValueChange = {
                lName = it
            })
            Spacer(modifier = Modifier.padding(3.dp))
            SimpleOutlinedEmailField(onValueChanged = {
                email = it
            })
            Spacer(modifier = Modifier.padding(3.dp))
            SimpleOutlinedPasswordTextField(onValueChange = {
                password = it
            })
            Spacer(modifier = Modifier.padding(3.dp))
            SimpleOutlinedPasswordTextField(label = "Confirm Password", onValueChange = {
                if (it == password) {
                    password = it
                    isValid = true
                } else {
                    isValid = false
                }
            })
            if (!isValid) {
                Text(
                    text = "Password does not match",
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            if (showLoader) {
                Text(
                    text = "Creating User.... Please wait",
                    color = Color.Blue,
                    modifier = Modifier.padding(8.dp)
                )
            } else {
                GradientButton(
                    nameButton = "Create An Account",
                    roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
                    btnClick = {
                        showLoader = true
                        viewModel?.createAccount(email, password, fName, lName)
                    }
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            TextButton(onClick = {
                navigator?.pop()
            }) {
                Text(
                    text = "Sign In",
                    letterSpacing = 1.sp,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}