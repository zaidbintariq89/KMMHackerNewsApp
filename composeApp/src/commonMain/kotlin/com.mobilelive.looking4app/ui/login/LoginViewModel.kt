package com.mobilelive.looking4app.ui.login

import com.mobilelive.looking4app.data.model.Article
import com.mobilelive.looking4app.data.repository.AppRepository
import com.mobilelive.looking4app.data.repository.DataState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val appRepository = AppRepository()
    val allArticlesResponse = MutableStateFlow<DataState<List<Article>>?>(DataState.Loading)

    fun isValidCredentials(email: String, password: String): Boolean {
        val emailPattern = Regex("[a-zA-Z0–9._-]+@[a-z]+\\.+[a-z]+")
//        val passwordPattern = Regex("^(?=.*[0–9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
        return emailPattern.matches(email) //&& passwordPattern.matches(password)
    }

    fun fetchAllArticles() {
        viewModelScope.launch {
            appRepository.getArticles().collectLatest {
                allArticlesResponse.value = it
            }
        }
    }
}