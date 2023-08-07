package com.example.kmmtestapp.kmm.shared

import com.example.kmmtestapp.kmm.shared.cache.Database
import com.example.kmmtestapp.kmm.shared.cache.DatabaseDriverFactory
import com.example.kmmtestapp.kmm.shared.entity.RocketLaunch
import com.example.kmmtestapp.kmm.shared.network.SpaceXApi


class SpaceXSDK (databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

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
}
