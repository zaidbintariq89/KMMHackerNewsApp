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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import com.mobilelive.looking4app.ui.common.GradientButton
import com.mobilelive.looking4app.ui.common.SimpleOutlinedEmailField
import com.mobilelive.looking4app.ui.common.SimpleOutlinedPasswordTextField
import com.mobilelive.looking4app.ui.common.SimpleTextField
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class RegisterScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val registerViewModel = RegisterViewModel()
        registerPageContent(navigator, registerViewModel)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun registerPageContent(navigator: Navigator?, viewModel: RegisterViewModel?) {
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
            SimpleTextField(label = "Name", hint = "Name", keyboardType = KeyboardType.Text, onValueChange = {
                //
            })
            Spacer(modifier = Modifier.padding(3.dp))
            SimpleTextField(label = "Phone", hint = "Phone", keyboardType = KeyboardType.Phone, onValueChange = {
                //
            })
            Spacer(modifier = Modifier.padding(3.dp))
            SimpleOutlinedEmailField(onValueChanged = {
                //email = it
            })
            Spacer(modifier = Modifier.padding(3.dp))
            SimpleOutlinedPasswordTextField(onValueChange = {
                //password = it
            })
            Spacer(modifier = Modifier.padding(3.dp))
            SimpleOutlinedPasswordTextField(label = "Confirm Password", onValueChange = {
                //password = it
            })
            Spacer(modifier = Modifier.padding(10.dp))
            GradientButton(
                nameButton = "Create An Account",
                roundedCornerShape = RoundedCornerShape(topStart = 30.dp,bottomEnd = 30.dp),
                btnClick = {

                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            TextButton(onClick = {
                navigator?.pop()
            }) {
                androidx.compose.material3.Text(
                    text = "Sign In",
                    letterSpacing = 1.sp,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}