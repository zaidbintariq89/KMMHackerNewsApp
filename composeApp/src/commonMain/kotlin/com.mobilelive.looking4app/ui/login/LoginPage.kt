package com.mobilelive.looking4app.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.mobilelive.looking4app.data.model.Article
import com.mobilelive.looking4app.data.repository.DataState
import com.mobilelive.looking4app.theme.LocalThemeIsDark
import com.mobilelive.looking4app.ui.common.GradientButton
import com.mobilelive.looking4app.ui.common.SimpleOutlinedEmailField
import com.mobilelive.looking4app.ui.common.SimpleOutlinedPasswordTextField
import com.mobilelive.looking4app.ui.register.RegisterScreen
import com.mobilelive.looking4app.ui.resetPassword.ResetPasswordScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        loginView()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun loginView() {
    val navigator = LocalNavigator.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(true) }
//    var showLoading by remember { mutableStateOf(false) }
    val loginViewModel = LoginViewModel()

    loginViewModel.allArticlesResponse.collectAsState().value?.let {
        when (it) {
            is DataState.Loading -> {
                println("Login::: Loading data")
            }

            is DataState.Success<List<Article>> -> {
                println("Login::: Result = ${it.data}")
//                showLoading = false
            }

            is DataState.Error -> {
                println(it.exception)
//                showLoading = false
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Transparent)
        ) {
//            if(showLoading) {
//                LinearProgressIndicator( modifier = Modifier.fillMaxWidth())
//            }
            Row(






                horizontalArrangement = Arrangement.End
            ) {
                var isDark by LocalThemeIsDark.current
                IconButton(
                    onClick = { isDark = !isDark }
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp).size(20.dp),
                        imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = null
                    )
                }
            }

            Box(modifier = Modifier
                .align(Alignment.BottomCenter),
            ) {
                Image(
                    painter = painterResource("user_sign_in.png"),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                )
                Column(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    //.........................Spacer
                    Spacer(modifier = Modifier.height(30.dp))
                    //.........................Text: title
                    Text(
                        text = "Sign In",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 130.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    SimpleOutlinedEmailField(onValueChanged = {
                        email = it
                    })
                    Spacer(modifier = Modifier.padding(3.dp))
                    SimpleOutlinedPasswordTextField(onValueChange = {
                        password = it
                    })

                    if (!isValid) {
                        Text(
                            text = "Invalid email or password",
                            color = Color.Red,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.padding(10.dp))

                    GradientButton(
                        nameButton = "Login",
                        roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
                        btnClick = {
                            println("Email = $email and Password = $password")
//                            isValid = loginViewModel.isValidCredentials(email, password)
//                            if (isValid) {
//                            }
//                            showLoading = true
                            loginViewModel.fetchAllArticles()
                        }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))
                    TextButton(onClick = {
                        navigator?.push(RegisterScreen())
                    }) {
                        Text(
                            text = "Create An Account",
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }


                    Spacer(modifier = Modifier.padding(5.dp))
                    TextButton(onClick = {

                        navigator?.push(ResetPasswordScreen())

                    }) {
                        Text(
                            text = "Reset Password",
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                    Spacer(modifier = Modifier.padding(20.dp))

                }


            }
        }
    }
}