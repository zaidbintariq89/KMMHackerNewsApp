package com.kmmhackernewsapp.shared.network.api

import com.kmmhackernewsapp.shared.entity.AccountsResponseModel
import com.kmmhackernewsapp.shared.entity.BillingResponseModel
import com.kmmhackernewsapp.shared.entity.PromotionsResponseModel
import com.kmmhackernewsapp.shared.entity.RocketLaunch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkApi {
    private val baseUrl = "https://demo5286530.mockable.io/"

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    private fun getRequestBuilder(url: String): HttpRequestBuilder {
        val requestBuilder = HttpRequestBuilder()
        requestBuilder.header("Accept", "application/json")
        requestBuilder.header("Content-Type", "application/json")
        requestBuilder.header("app", "kmm")
        requestBuilder.url(baseUrl.plus(url))
        return requestBuilder
    }

    /**
     * @params null
     * @return List of rocketLaunches in ascending order
     */
    suspend fun getAllLaunches(): List<RocketLaunch> {
        return httpClient.get("https://api.spacexdata.com/v5/launches").body()
    }

    suspend fun getAllAccounts(): AccountsResponseModel {
        return httpClient.get(getRequestBuilder("accounts")).body()
    }

    suspend fun getPromotions(): PromotionsResponseModel {
        return httpClient.get(getRequestBuilder("promotions")).body()
    }

    suspend fun getBillingData(): BillingResponseModel {
        return httpClient.get(getRequestBuilder("billing")).body()
    }

}