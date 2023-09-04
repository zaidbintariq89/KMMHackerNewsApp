package com.kmmhackernewsapp.android.ui.rocket

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmmhackernewsapp.shared.cache.DatabaseDriverFactory
import com.kmmhackernewsapp.shared.entity.RocketLaunch
import com.kmmhackernewsapp.shared.network.SpaceXSDK
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    // create live data
    private val _rocketsLiveData = MutableLiveData<List<RocketLaunch>>()
    val rocketsLiveData: LiveData<List<RocketLaunch>> = _rocketsLiveData

    fun fetchDataForRockets(context: Context) {
        val spaceXSdk = SpaceXSDK(DatabaseDriverFactory(context))
        viewModelScope.launch {
            kotlin.runCatching {
                spaceXSdk.getLaunches(forceReload = false)
            }.onSuccess {
                Log.d("MainViewModel", "Data received = $it")
                it.takeIf { it.isNotEmpty() }?.let { rockets ->
                    _rocketsLiveData.value = rockets.sortedByDescending { rocket-> rocket.launchYear }
                } ?: kotlin.run {
                    _rocketsLiveData.value = emptyList()
                }
            }.onFailure {
                Log.d("MainViewModel", "Data fetch error")
                _rocketsLiveData.value = emptyList()
            }

        }
    }
}