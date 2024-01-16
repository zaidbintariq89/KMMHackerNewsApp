package com.mobilelive.looking4app.data.repository

import com.mobilelive.looking4app.data.remote.ApiImpl
import kotlinx.coroutines.flow.flow

class AppRepository {
    private val api = ApiImpl()

    fun getArticles() = flow {
        emit(DataState.Loading)
        try {
            val result = api.getAllArticles()
            emit(DataState.Success(result))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}