package com.mobilelive.looking4app.ui.resetPassword

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.mobilelive.looking4app.ui.common.GradientButton
import com.mobilelive.looking4app.ui.common.SimpleOutlinedEmailField
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class ResetPasswordScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val resetPasswordVM = ResetViewModel()
        resetPage(navigator)
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    fun resetPage(navigator: Navigator?) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = Color.Transparent,
                )
        ) {


            Box(
                modifier = Modifier
                    /*.background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                    )*/
                    .align(Alignment.BottomCenter),
            ) {

                Image(
                    painter = painterResource("user_forgot.png"),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth(),

                    )
                Column(
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                    ,

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    //.........................Spacer
                    Spacer(modifier = Modifier.height(50.dp))

                    //.........................Text: title
                    androidx.compose.material3.Text(
                        text = "Reset Password",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 130.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    SimpleOutlinedEmailField {

                    }
                    Spacer(modifier = Modifier.padding(3.dp))

//                    Spacer(modifier = Modifier.padding(10.dp))
//                    /* Button(
//                         onClick = {},
//                         modifier = Modifier
//                             .fillMaxWidth(0.8f)
//                             .height(50.dp)
//                     ) {
//                         Text(text = "Login", fontSize = 20.sp)
//                     }*/
                    GradientButton(nameButton = "Submit", roundedCornerShape = RoundedCornerShape(topStart = 30.dp,bottomEnd = 30.dp)) {

                    }

                    Spacer(modifier = Modifier.padding(10.dp))
                    androidx.compose.material3.TextButton(onClick = {

                        navigator?.pop()

                    }) {
                        androidx.compose.material3.Text(
                            text = "Login",
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    Spacer(modifier = Modifier.padding(5.dp))


                }


            }

        }
    }
}