package com.mobilelive.looking4app.services

import com.example.routing.request.UserRequest
import com.example.routing.response.UserResponseWithLocationsServices
import com.example.routing.response.toResponse
import com.mobilelive.looking4app.exposed.Location
import com.mobilelive.looking4app.exposed.Service
import com.mobilelive.looking4app.exposed.User
import com.mobilelive.looking4app.repository.UserRepository
import java.util.*

class UserService(
    private val userRepository: UserRepository
) {
    fun findAll(): List<User> = userRepository.findAll()

    fun findById(id: Int): User? = userRepository.findById(id)

    fun findByUsername(username: String): User? = userRepository.findByUsername(username)

    fun save(user: User): User? {
        val foundUser = findByUsername(user.username)
        return if (foundUser == null) {
            userRepository.save(user)
        } else null
    }
    fun login(username: String, password: String): User? =
        userRepository.findByUsernameAndPassword(username, password)

    fun updateUser(id: Int, userUpdateRequest: UserRequest): User {
        val existingUser = userRepository.findById(id)
            ?: throw NoSuchElementException("User not found.")

        val updatedUser = existingUser.copy(
            username = userUpdateRequest.username,
            firstName = userUpdateRequest.firstName,
            lastName = userUpdateRequest.lastName,
            password = userUpdateRequest.password,
            userRole = userUpdateRequest.userRole
        )

        return userRepository.updateUser(updatedUser)
    }

    fun addUserLocations(userId: Int, locationIds: List<Int>) {
        userRepository.addUserLocations(userId, locationIds)
    }

    fun getUserLocations(userId: Int): List<Location> {
        return userRepository.getUserLocations(userId)
    }

    fun getUserServices(userId: Int): List<Service> {
        return userRepository.getUserServices(userId)
    }

    fun addUserServices(userId: Int, serviceIds: List<Int>) {
        userRepository.addUserServices(userId, serviceIds)
    }

}



// In UserService
fun User.toResponseWithLocationsServices(locations: List<Location>, services: List<Service>): UserResponseWithLocationsServices {
    return UserResponseWithLocationsServices(
        username = this.username,
        firstName = this.firstName,
        lastName = this.lastName,
        userRole = this.userRole,
        locations = locations.map { it.toResponse() },
        services = services.map { it.toResponse() }

    )
}
