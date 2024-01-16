package com.mobilelive.looking4app.repository

import com.mobilelive.looking4app.exposed.Service
import com.mobilelive.looking4app.exposed.Services
import com.mobilelive.looking4app.exposed.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ServiceRepository {

    fun findAll(): List<Service> = transaction {
        Services.selectAll().map { rowToService(it) }
    }

    fun findById(id: Int): Service? = transaction {
        Services.select { Services.id eq id }
            .singleOrNull()
            ?.let { rowToService(it) }
    }


    fun searchByName(name: String): List<Service> = transaction {
        Services.select { Services.name like "%$name%" }
            .map { rowToService(it) }
    }

    fun searchByDescription(description: String): List<Service> = transaction {
        Services.select { Services.description like "%$description%" }
            .map { rowToService(it) }
    }

    fun save(service: Service): Service {
        val id = transaction {
            Services.insert {
                it[name] = service.name
                it[description] = service.description
            } get Services.id
        }

        return findById(id) ?: throw IllegalStateException("Service not found after saving")
    }


    private fun rowToService(row: ResultRow): Service {
        return Service(
            id = row[Services.id],
            name = row[Services.name],
            description = row[Services.description]
        )
    }
}
