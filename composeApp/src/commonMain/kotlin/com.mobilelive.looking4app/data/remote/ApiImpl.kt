package com.mobilelive.looking4app.data.remote

import com.mobilelive.looking4app.data.model.Article
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
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
}