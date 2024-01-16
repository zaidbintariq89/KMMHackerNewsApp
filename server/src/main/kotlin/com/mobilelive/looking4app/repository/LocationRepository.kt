package com.mobilelive.looking4app.repository

import com.mobilelive.looking4app.exposed.Location
import com.mobilelive.looking4app.exposed.Locations
import com.mobilelive.looking4app.exposed.Services
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class LocationRepository {

    fun findAll(): List<Location> = transaction {
        Locations.selectAll().map { rowToLocation(it) }
    }

    fun findById(id: Int): Location? = transaction {
        Locations.select { Locations.id eq id }
            .singleOrNull()
            ?.let { rowToLocation(it) }
    }

    fun save(location: Location): Location {
        val id = transaction {
            Locations.insert {
                it[name] = location.name
            } get Locations.id
        }

        return findById(id) ?: throw IllegalStateException("Location not found after saving")
    }

    private fun rowToLocation(row: ResultRow): Location {
        return Location(
            id = row[Locations.id],
            name = row[Locations.name]
        )
    }
}