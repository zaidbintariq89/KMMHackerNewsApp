package com.kmmhackernewsapp.android.ui.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmmhackernewsapp.android.MainApplication
import kotlinx.coroutines.launch

class ServicesViewModel: ViewModel() {

    private val networkRepo = MainApplication.getNetworkRepo()

    fun getText() = "This is service tab"

    fun getAllAccounts() {
        viewModelScope.launch {
            kotlin.runCatching {
                networkRepo.getAllAccounts()
            }.onSuccess {

            }.onFailure {

            }
        }
    }
}