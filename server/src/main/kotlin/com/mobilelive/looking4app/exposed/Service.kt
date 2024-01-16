package com.mobilelive.looking4app.exposed
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Service(
    val id: Int,
    val name: String,
    val description: String
)

object Services : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val description = varchar("description", 1000)

    override val primaryKey = PrimaryKey(id)
}