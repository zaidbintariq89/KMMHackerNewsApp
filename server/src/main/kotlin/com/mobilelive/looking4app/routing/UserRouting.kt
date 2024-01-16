package com.mobilelive.looking4app.routing

import com.example.routing.request.UserLoginRequest
import com.example.routing.request.UserRequest
import com.example.routing.response.UserResponse
import com.mobilelive.looking4app.exposed.Location
import com.mobilelive.looking4app.exposed.User
import com.mobilelive.looking4app.services.UserService
import com.mobilelive.looking4app.services.toResponseWithLocationsServices
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*
import kotlin.text.get


fun Route.userRoute(userService: UserService) {


    post("/signup") {
        val userRequest = call.receive<UserRequest>()

        val existingUser = userService.findByUsername(userRequest.username)
        if (existingUser != null) {
            call.respond(HttpStatusCode.Conflict, "User with this username already exists")
            return@post
        }

        val createdUser = userService.save(
            user = userRequest.toModel()
        )

        if (createdUser != null) {
            call.response.header(
                name = "id",
                value = createdUser.id.toString()
            )
            call.respond(HttpStatusCode.Created)
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    post("/login") {
        val userRequest = call.receive<UserLoginRequest>()
        val user = userService.login(userRequest.username, userRequest.password)

        if (user != null) {
//            val token = generateJwtToken(user)
//            call.respond(mapOf("token" to token))
            call.respond(HttpStatusCode.OK)

        } else {
            call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
        }
    }

//    authenticate("jwt") {
//        post("/changepassword") {
//            val userRequest = call.receive<UserRequest>()
//            val userId = call.principal<User>()?.id ?: return@post call.respond(HttpStatusCode.Unauthorized)
//
//            if (userService.updatePassword(userId, userRequest.password)) {
//                call.respond(HttpStatusCode.OK, "Password updated successfully")
//            } else {
//                call.respond(HttpStatusCode.InternalServerError, "Failed to update password")
//            }
//        }
//    }

    put("/{id}") {
        val userId: String = call.parameters["id"]
            ?: return@put call.respond(HttpStatusCode.BadRequest, "User ID is required for updating.")

        try {
            val userIdInt = userId.toInt()
            val userUpdateRequest = call.receive<UserRequest>()
            val updatedUser = userService.updateUser(userIdInt, userUpdateRequest)
            call.respond(updatedUser)
        } catch (e: NumberFormatException) {
            call.respond(HttpStatusCode.BadRequest, "Invalid User ID format.")
        } catch (e: NoSuchElementException) {
            call.respond(HttpStatusCode.NotFound, "User not found.")
        }
    }
    post("/addUserLocations/{id}") {
        val userId: Int = call.parameters["id"]?.toIntOrNull()
            ?: return@post call.respond(HttpStatusCode.BadRequest)

        val locationNames: List<Int> = call.receive()
        userService.addUserLocations(userId, locationNames)
        call.respond(HttpStatusCode.OK)
    }

    post("/addUserService/{id}") {
        val userId: Int = call.parameters["id"]?.toIntOrNull()
            ?: return@post call.respond(HttpStatusCode.BadRequest)

        val serviceId: List<Int>  = call.receive()
        userService.addUserServices(userId, serviceId)
        call.respond(HttpStatusCode.OK)
    }

    post {
            val userRequest = call.receive<UserRequest>()

            val createdUser = userService.save(
                user = userRequest.toModel()
            )

            if (createdUser != null) {
                call.response.header(
                    name = "id",
                    value = createdUser.id.toString()
                )
                call.respond(HttpStatusCode.Created)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        get {
            val users = userService.findAll()

            call.respond(users.map {
                it.toResponse()
            })
        }

        get("/{id}") {
            val id: Int = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val foundUser = userService.findById(id)

            if (foundUser != null) {
                val userResponse = foundUser.toResponseWithLocationsServices(userService.getUserLocations(id), userService.getUserServices(id))
                call.respond(userResponse)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }


private fun UserRequest.toModel(): User = User(
    username = this.username,
    password = this.password,
    firstName = this.firstName,
    lastName = this.lastName,
    userRole = this.userRole,
    id = 1
)

private fun  User.toResponse(): UserResponse =
    UserResponse(username = this.username, firstName = this.firstName, lastName = this.lastName, userRole = this.userRole)

//private fun generateJwtToken(user: User): String {
//    val secret = "your-secret-key" // Replace with your secret key
//    return JWT.create()
//        .withSubject("Authentication")
//        .withClaim("id", user.id)
//        .sign(Algorithm.HMAC256(secret))
//}