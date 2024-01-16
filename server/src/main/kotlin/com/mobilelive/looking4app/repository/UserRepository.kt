package com.mobilelive.looking4app.repository

import com.mobilelive.looking4app.exposed.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserRepository {
    fun findAll(): List<User> = transaction {
        Users.selectAll().map { rowToUser(it) }
    }

    fun findById(id: Int): User? = transaction {
        Users.select { Users.id eq id }
            .singleOrNull()
            ?.let { rowToUser(it) }
    }

    fun findByUsername(username: String): User? = transaction {
        Users.select { Users.username eq username }
            .singleOrNull()
            ?.let { rowToUser(it) }
    }

    fun save(user: User): User {
        val id = transaction {
            Users.insert {
                it[Users.username] = user.username
                it[Users.firstName] = user.firstName
                it[Users.lastName] = user.lastName
                it[Users.password] = user.password
                it[Users.userRole] = user.userRole
            } get Users.id
        }
        return findById(id) ?: throw IllegalStateException("User not found after saving")
    }


    fun findByUsernameAndPassword(username: String, password: String): User? = transaction {
        Users.select { Users.username.eq(username) and Users.password.eq(password) }
            .singleOrNull()
            ?.let { rowToUser(it) }
    }

    fun updatePassword(id: Int, newPassword: String): Boolean = transaction {
        Users.update({ Users.id eq id }) {
            it[Users.password] = newPassword
        } > 0
    }


    fun updateUser(user: User): User = transaction {
        Users.update({ Users.id eq user.id }) {
            it[Users.username] = user.username
            it[Users.firstName] = user.firstName
            it[Users.lastName] = user.lastName
            it[Users.password] = user.password
            it[Users.userRole] = user.userRole
            // Update other fields as needed
        }

        findById(user.id) ?: throw IllegalStateException("User not found after updating")
    }

    fun addUserLocations(userId: Int, locationIds: List<Int>) {
        transaction {
            // Add new associations
            locationIds.forEach { locationId ->
                UserLocations.insert {
                    it[UserLocations.userId] = userId
                    it[UserLocations.locationId] = locationId
                }
            }
        }
    }

    fun getUserLocations(userId: Int): List<Location> = transaction {
        UserLocations.select { UserLocations.userId eq userId }
            .map { rowToLocation(it) }
    }

    fun getUserServices(userId: Int): List<Service> = transaction {
        UserServices.select { UserServices.userId eq userId }
            .map { rowToService(it) }
    }

    fun addUserServices(userId: Int, serviceIds: List<Int>) = transaction {
        // Remove existing user services

        // Add new user services
        serviceIds.forEach { serviceId ->
            UserServices.insert {
                it[UserServices.userId] = userId
                it[UserServices.serviceId] = serviceId
            }
        }
    }

    private fun rowToService(row: ResultRow): Service {
        return Service(
            id = row[UserServices.serviceId],
            name = Services.select { Services.id eq row[UserServices.serviceId] }
                .single()[Services.name],
            description = Services.select { Services.id eq row[UserServices.serviceId] }
                .single()[Services.description]
        )
    }

    private fun rowToLocation(row: ResultRow): Location {
        return Location(
            id = row[UserLocations.locationId],
            name = Locations.select { Locations.id eq row[UserLocations.locationId] }
                .single()[Locations.name]
        )
    }
    private fun rowToUser(row: ResultRow): User {
        return User(
            id = row[Users.id],
            username = row[Users.username],
            firstName = row[Users.firstName],
            lastName = row[Users.lastName],
            password = row[Users.password],
            userRole = row[Users.userRole]
        )
    }
}
