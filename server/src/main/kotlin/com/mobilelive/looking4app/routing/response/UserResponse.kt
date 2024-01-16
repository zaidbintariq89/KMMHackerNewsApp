package com.example.routing.response

import com.mobilelive.looking4app.exposed.Location
import com.mobilelive.looking4app.exposed.Service
import jdk.jfr.Description
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserResponse (
    val  username: String,
    val  firstName: String,
    val  lastName: String,
    val  userRole: String,
    )

@Serializable
data class UserResponseWithLocationsServices(
    val username: String,
    val firstName: String,
    val lastName: String,
    val userRole: String,
    val locations: List<LocationResponse>,
    val services: List<ServicesResponse>

)

fun Location.toResponse(): LocationResponse {
    return LocationResponse(id, name)
}

fun Service.toResponse(): ServicesResponse {
    return ServicesResponse(id, name, description)
}


@Serializable
data class LocationResponse(
    val id: Int,
    val name: String
)


@Serializable
data class ServicesResponse(
    val id: Int,
    val name: String,
    val description: String
)