package com.kmmhackernewsapp.android.ui.usage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmmhackernewsapp.android.MainApplication
import com.kmmhackernewsapp.shared.entity.PromotionsResponseModel
import kotlinx.coroutines.launch

class UsageViewModel : ViewModel() {
    private val networkRepo = MainApplication.getNetworkRepo()

    private val _promotionLV: MutableLiveData<PromotionsResponseModel?> = MutableLiveData()
    val promotionLiveData: LiveData<PromotionsResponseModel?> = _promotionLV

    fun getAllPromotions() {
        viewModelScope.launch {
            kotlin.runCatching {
                networkRepo.getPromotions()
            }.onSuccess {
                _promotionLV.postValue(it)
            }.onFailure {
                _promotionLV.postValue(null)
            }
        }
    }
}