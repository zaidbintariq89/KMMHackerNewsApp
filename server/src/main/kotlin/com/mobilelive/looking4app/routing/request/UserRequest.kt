package com.example.routing.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest (

    val  username: String,
    val  firstName: String,
    val  lastName: String,
    val  password: String,
    val  userRole: String,

    )


@Serializable
data class UserLoginRequest (

    val  username: String,
    val  password: String,

    )


@Serializable
data class ServiceRequest (

    val  name: String,
    val  description: String,

    )

@Serializable
data class LocationRequest (

    val  name: String,

    )
