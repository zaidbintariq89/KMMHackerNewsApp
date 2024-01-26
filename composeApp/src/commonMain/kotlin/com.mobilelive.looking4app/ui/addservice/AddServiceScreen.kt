package com.mobilelive.looking4app.ui.addservice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.mobilelive.looking4app.data.repository.DataState
import com.mobilelive.looking4app.ui.common.GradientButton
import com.mobilelive.looking4app.ui.common.ProgressIndicator
import com.mobilelive.looking4app.ui.common.SimpleTextField
import com.mobilelive.looking4app.ui.tabs.HomeViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi

object AddServiceScreen : Tab {
    private val homeViewModel = HomeViewModel()
    override val options: TabOptions
        @Composable
        get() {
            val title = "Service"
            val icon = rememberVectorPainter(Icons.Default.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        homeViewModel.createServiceResponse.collectAsState().value.let { response ->
            when (response) {
                is DataState.Loading -> {
                    ProgressIndicator()
                }

                is DataState.Success -> {
                    println("Services::: Success :: ${response.data}")
                    navigator.pop()
                }

                is DataState.Error -> {
                    println(response.exception)
                }
                else -> {}
            }
        }
        CreateServiceView(navigator)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CreateServiceView(navigator: Navigator) {
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Add Service",
                            maxLines = 1,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator.pop()
                        }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                    )
                )
            },
        ) { padding ->
            ServiceContent(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
            ) {
                LayoutView()
            }
        }
    }

    @Composable
    fun ServiceContent(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Column(modifier) {
            content()
        }
    }

    @OptIn(ExperimentalResourceApi::class, ExperimentalComposeUiApi::class)
    @Composable
    fun LayoutView() {
        var name by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val keyboardController = LocalSoftwareKeyboardController.current
                SimpleTextField(label = "Service Name", hint = "Service", keyboardType = KeyboardType.Text, onValueChange = {
                    name = it
                })
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange =
                    {
                        description = it
                    },
                    shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
                    label = {
                        Text(
                            "Description",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium,
                        )
                    },
                    placeholder = { Text(text = "Description") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
                    singleLine = true,
                    modifier = Modifier.height(150.dp),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    )
                )
                Spacer(modifier = Modifier.padding(10.dp))

                GradientButton(
                    nameButton = "Save",
                    roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
                    btnClick = {
                        homeViewModel.createService(name, description)
                    }
                )
            }
        }
    }
}