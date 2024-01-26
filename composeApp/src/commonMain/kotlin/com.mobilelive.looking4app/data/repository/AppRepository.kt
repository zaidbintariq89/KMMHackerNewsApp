package com.mobilelive.looking4app.data.repository

import com.mobilelive.looking4app.data.remote.ApiImpl
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.flow

class AppRepository {
    private val api = ApiImpl()

//    fun getArticles() = flow {
//        emit(DataState.Loading)
//        try {
//            val result = api.getAllArticles()
//            emit(DataState.Success(result))
//        } catch (e: Exception) {
//            emit(DataState.Error(e.message ?: ""))
//        }
//    }

//    suspend fun login(userName: String, password: String): Flow<NetWorkResult<Any>> {
//        return toResultFlow {
//            val response = api.doLogin(userName, password)
//            if (response.status.isSuccess()) {
//                NetWorkResult.Success(response)
//            } else {
//                NetWorkResult.Error(null,"Invalid Credentials")
//            }
//        }
//    }

    fun doLogin(userName: String, password: String) = flow {
        emit(DataState.Loading)
        try {
            val result = api.doLogin(userName, password)
            if (result.status.isSuccess()) {
                emit(DataState.Success(result))
            } else {
                emit(DataState.Error("Invalid Credentials"))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: ""))
        }
    }

    fun signUp(username: String, password: String, firstName: String, lastName: String) = flow {
        emit(DataState.Loading)
        try {
            val result = api.signUp(username, password, firstName, lastName)
            if (result.status.isSuccess()) {
                emit(DataState.Success(result))
            } else {
                emit(DataState.Error("username is already exist"))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: ""))
        }
    }

    fun getAllServices() = flow {
        try {
            val result = api.getAllServices()
            emit(DataState.Success(result))
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: ""))
        }
    }

    fun createService(name: String, description: String) = flow {
        try {
            val result = api.createService(name, description)
            emit(DataState.Success(result))
        } catch (e: Exception) {
            emit(DataState.Error(e.message ?: ""))
        }
    }
}