package com.mobilelive.looking4app.data.remote

import ServiceRequest
import UserLoginRequest
import UserRequest
import com.mobilelive.looking4app.data.model.Article
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath

class ApiImpl : ApiInterface {
    private fun HttpRequestBuilder.getArticles() {
        url {
            encodedPath = "articles"
        }
    }
    override suspend fun getAllArticles(): List<Article> {
        return client.get {
            getArticles()
        }.body()
    }

    private fun HttpRequestBuilder.login(userName: String , password: String) {
        url {
            encodedPath = "api/user/login"
            contentType(ContentType.Application.Json)
            setBody(UserLoginRequest(userName, password))
        }
    }

    private fun HttpRequestBuilder.signUp(username: String, password: String, firstName: String, lastName: String) {
        url {
            encodedPath = "api/user/signup"
            contentType(ContentType.Application.Json)
            setBody(UserRequest(username, firstName, lastName, password, "admin"))
        }
    }

    override suspend fun doLogin(userName: String , password: String): HttpResponse {
        return client.post {
            login(userName, password)
        }.body()
    }

    override suspend fun signUp(
        username: String,
        password: String,
        firstName: String,
        lastName: String
    ): HttpResponse {
        return client.post {
            signUp(username, password, firstName, lastName)
        }.body()
    }

    override suspend fun getAllServices(): List<ServiceRequest> {
        return client.get {
            url {
                encodedPath = "api/user/services"
            }
        }.body()
    }

    override suspend fun createService(name: String, description: String): ServiceRequest {
        return client.post {
            url {
                encodedPath = "api/user/services"
                contentType(ContentType.Application.Json)
                setBody(ServiceRequest(name = name, description = description))
            }
        }.body()
    }
}