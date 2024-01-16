package com.mobilelive.looking4app.routing

import com.example.routing.request.ServiceRequest
import com.mobilelive.looking4app.exposed.Service
import com.mobilelive.looking4app.services.ServiceService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.serviceRoute(serviceService: ServiceService) {
    route("/services") {
        // Existing routes...

        get {
            val services = serviceService.findAll()
            call.respond(services)
        }

        get("/{id}") {
            val id: Int = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val service = serviceService.findById(id)
            if (service != null) {
                call.respond(service)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        get("/searchByName/{name}") {
            val name: String = call.parameters["name"]
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val services = serviceService.searchByName(name)
            call.respond(services)
        }

        get("/searchByDescription/{description}") {
            val description: String = call.parameters["description"]
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val services = serviceService.searchByDescription(description)
            call.respond(services)
        }

        post {
            val service = call.receive<ServiceRequest>()
            val createdService = serviceService.save(Service(id = 1, name = service.name, description = service.description))
            call.respond(createdService)
        }

    }
}

