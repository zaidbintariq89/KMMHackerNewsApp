package com.kmmhackernewsapp.android.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmmhackernewsapp.android.ui.login.model.LoginErrorModel
import com.kmmhackernewsapp.android.ui.login.model.LoginResponseModel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            when(val result = loginRepository.signIn(email, password)) {
                is LoginResponseModel -> {
                    Log.d("LoginViewModel", "Successfully signed in with ${result.username}")
                }
                is LoginErrorModel -> {
                    Log.d("LoginViewModel", "Signed in Fail :: ${result.error}")
                }
            }
        }
    }
}