package com.kmmhackernewsapp.android.ui.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmmhackernewsapp.android.MainApplication
import com.kmmhackernewsapp.shared.entity.AccountsResponseModel
import kotlinx.coroutines.launch

class ServicesViewModel: ViewModel() {
    private val networkRepo = MainApplication.getNetworkRepo()

    private val _accountLV: MutableLiveData<AccountsResponseModel?> = MutableLiveData()
    val accountsLiveData: LiveData<AccountsResponseModel?> = _accountLV

    fun getText() = "This is service tab"

    fun getAllAccounts() {
        viewModelScope.launch {
            kotlin.runCatching {
                networkRepo.getAllAccounts()
            }.onSuccess {
                _accountLV.postValue(it)
            }.onFailure {
                _accountLV.postValue(null)
            }
        }
    }
}