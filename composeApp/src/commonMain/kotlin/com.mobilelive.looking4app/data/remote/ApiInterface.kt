package com.mobilelive.looking4app.data.remote

import ServiceRequest
import com.mobilelive.looking4app.data.model.Article
import io.ktor.client.statement.HttpResponse

interface ApiInterface {
    suspend fun getAllArticles() : List<Article>

    suspend fun doLogin(userName: String , password: String) : HttpResponse

    suspend fun signUp(username: String, password: String, firstName: String, lastName: String) : HttpResponse

    suspend fun getAllServices() : List<ServiceRequest>

    suspend fun createService(name: String, description: String) : ServiceRequest
}