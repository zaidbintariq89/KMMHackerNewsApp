package com.mobilelive.looking4app.ui.register

import com.mobilelive.looking4app.data.repository.AppRepository
import com.mobilelive.looking4app.data.repository.DataState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val appRepository = AppRepository()
    val signUpResponse = MutableStateFlow<DataState<Any>>(DataState.Content)

    fun createAccount(username: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            appRepository.signUp(username, password, firstName, lastName).collectLatest {
                signUpResponse.value = it
            }
        }
    }
}