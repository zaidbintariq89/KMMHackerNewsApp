package com.mobilelive.looking4app.ui.tabs

import ServiceRequest
import com.mobilelive.looking4app.data.repository.AppRepository
import com.mobilelive.looking4app.data.repository.DataState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val appRepository = AppRepository()
    val serviceResponse = MutableStateFlow<DataState<List<ServiceRequest>>?>(DataState.Content)
    val createServiceResponse = MutableStateFlow<DataState<ServiceRequest>?>(DataState.Content)

    fun getAllServices() {
        viewModelScope.launch {
            serviceResponse.value = DataState.Loading
            appRepository.getAllServices().collectLatest {
                serviceResponse.value = it
            }
        }
    }

    fun createService(name: String, description: String) {
        viewModelScope.launch {
            createServiceResponse.value = DataState.Loading
            appRepository.createService(name, description).collectLatest {
                createServiceResponse.value = it
            }
        }
    }
}