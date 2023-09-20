package com.kmmhackernewsapp.android.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmmhackernewsapp.android.ui.login.model.LoginErrorModel
import com.kmmhackernewsapp.android.ui.login.model.LoginResponseModel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository()
    private val _loginObserver = MutableLiveData<LoginResponseModel>()
    val loginObserver: LiveData<LoginResponseModel> = _loginObserver

    fun checkIfUserLogin() : Boolean {
        return loginRepository.checkIfLogin()?.let {
            true
        }    ?: kotlin.run {
            false
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            when(val result = loginRepository.signIn(email, password)) {
                is LoginResponseModel -> {
                    Log.d("LoginViewModel", "Successfully signed in with ${result.username}")
                    _loginObserver.postValue(result)
                }
                is LoginErrorModel -> {
                    Log.d("LoginViewModel", "Signed in Fail :: ${result.error}")
                    _loginObserver.postValue(null)
                }
            }
        }
    }
}