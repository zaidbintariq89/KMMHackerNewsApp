package com.mobilelive.looking4app.exposed

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table


@Serializable
data class User(
    val id: Int,
    val username: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val userRole: String,
    val locations: List<Location> = emptyList(),
    val offeredServices: List<Service> = emptyList()
)

object Users : Table() {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 50).uniqueIndex()
    val firstName = varchar("firstName", 50)
    val lastName = varchar("lastName", 50)
    val password = varchar("password", 50)
    val userRole = varchar("userRole", 50)

    override val primaryKey = PrimaryKey(id)
}