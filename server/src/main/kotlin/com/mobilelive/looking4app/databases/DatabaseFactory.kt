package com.mobilelive.looking4app.databases

import com.mobilelive.looking4app.exposed.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val database =  Database.connect(
            url = "jdbc:postgresql://ktor-app-database.cmjk86uuh4kt.us-east-2.rds.amazonaws.com:5432/looking4ca",
            driver = "org.postgresql.Driver",
            user = "root",
            password = "49Op64ImMt"
        )

        transaction(database) {
            SchemaUtils.create(Users, Locations, Services, UserServices, UserLocations)
        }
    }
    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}