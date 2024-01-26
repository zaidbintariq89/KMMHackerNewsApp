package com.mobilelive.looking4app.routing

import com.mobilelive.looking4app.routing.request.LocationRequest
import com.mobilelive.looking4app.exposed.Location
import com.mobilelive.looking4app.services.LocationService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.locationRoute(locationService: LocationService) {
    route("/locations") {
        get {
            val locations = locationService.findAll()
            call.respond(locations)
        }

        get("/{id}") {
            val id: Int = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val location = locationService.findById(id)
            if (location != null) {
                call.respond(location)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post {
            val location = call.receive<LocationRequest>()
            val createdLocation = locationService.save(Location(id = 1, name = location.name))
            call.respond(createdLocation)
        }
    }
}