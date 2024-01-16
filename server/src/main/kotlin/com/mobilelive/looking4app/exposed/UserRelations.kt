package com.mobilelive.looking4app.exposed

import org.jetbrains.exposed.sql.Table


object UserServices : Table() {
    val userId = integer("user_id") references Users.id
    val serviceId = integer("service_id") references Services.id

    override val primaryKey = PrimaryKey(userId, serviceId)
}

object UserLocations : Table() {
    val userId = integer("user_id") references Users.id
    val locationId = integer("location_id") references Locations.id

    override val primaryKey = PrimaryKey(userId, locationId)
}