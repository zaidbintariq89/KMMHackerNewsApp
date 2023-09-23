package com.kmmhackernewsapp.shared.network

import com.kmmhackernewsapp.shared.cache.Database
import com.kmmhackernewsapp.shared.cache.DatabaseDriverFactory
import com.kmmhackernewsapp.shared.entity.AccountsResponseModel
import com.kmmhackernewsapp.shared.entity.BillingResponseModel
import com.kmmhackernewsapp.shared.entity.PromotionsResponseModel
import com.kmmhackernewsapp.shared.entity.RocketLaunch
import com.kmmhackernewsapp.shared.network.api.NetworkApi


class NetworkRepo (databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = NetworkApi()

    /**
     * @param forceReload: true if requires to force reload
     * @return List of Rocket Launches from db if it is not empty
     */
    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }

    suspend fun getAllAccounts(): AccountsResponseModel {
        return api.getAllAccounts()
    }
    suspend fun getPromotions(): PromotionsResponseModel {
        return api.getPromotions()
    }
    suspend fun getBillingData(): BillingResponseModel {
        return api.getBillingData()
    }
}
