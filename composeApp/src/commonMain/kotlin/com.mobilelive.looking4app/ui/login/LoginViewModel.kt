package com.mobilelive.looking4app.ui.login

import CommonValidator
import com.mobilelive.looking4app.data.ApiStatus
import com.mobilelive.looking4app.data.repository.AppRepository
import com.mobilelive.looking4app.data.repository.DataState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val appRepository = AppRepository()
//    val allArticlesResponse = MutableStateFlow<DataState<List<Article>>?>(DataState.Loading)
    val loginResponse = MutableStateFlow<DataState<Any>>(DataState.Content)
//    val screenState: StateFlow<DataState<Any>>
//        get() = loginResponse

    fun isValidCredentials(email: String, password: String): Boolean {
        return email.isNotBlank() && password.isNotBlank()//CommonValidator.isValidPassword(password)
    }

//    fun fetchAllArticles() {
//        viewModelScope.launch {
//            appRepository.getArticles().collectLatest {
//                allArticlesResponse.value = it
//            }
//        }
//    }

    fun doLogin(userName: String, password: String) {
        viewModelScope.launch {
            appRepository.doLogin(userName, password).collectLatest {
                loginResponse.value = it
            }
//            appRepository.login(userName, password).collect { response ->
//                when (response.status) {
//                    ApiStatus.LOADING -> {
//                    }
//
//                    ApiStatus.SUCCESS -> {
//                        loginResponse.value = DataState.Success("Login Success")
//                    }
//
//                    ApiStatus.ERROR -> {
//                        response.message?.let {
//                            loginResponse.value = DataState.Error(it)
//                        }
//                    }
//                }
//            }
        }
    }
}