package com.mobilelive.looking4app

import SERVER_PORT
import com.mobilelive.looking4app.dao.ArticleDAO
import com.mobilelive.looking4app.dao.ArticleDAOImpl
import com.mobilelive.looking4app.databases.DatabaseFactory
import com.mobilelive.looking4app.repository.LocationRepository
import com.mobilelive.looking4app.repository.ServiceRepository
import com.mobilelive.looking4app.repository.UserRepository
import com.mobilelive.looking4app.routing.locationRoute
import com.mobilelive.looking4app.routing.serviceRoute
import com.mobilelive.looking4app.routing.userRoute
import com.mobilelive.looking4app.services.LocationService
import com.mobilelive.looking4app.services.ServiceService
import com.mobilelive.looking4app.services.UserService
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun main() {
    embeddedServer(Netty, port = (System.getenv("PORT") ?: "$SERVER_PORT").toInt(), host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val articleDao = ArticleDAOImpl()
    DatabaseFactory.init()
    configureRouting(articleDao)
    configureSerialization()
}

fun Application.configureRouting(articleDAO: ArticleDAO) {
    routing {

        val userRepository = UserRepository()
        val userService = UserService(userRepository)


        val serviceRepository = ServiceRepository()
        val serviceService = ServiceService(serviceRepository)

        val locationRepository = LocationRepository()
        val locationService = LocationService(locationRepository)


        route("/api/user") {
            userRoute(userService)
            serviceRoute(serviceService)
            locationRoute(locationService)

        }


        get("/") {
            call.respondText("Ktor: server")
        }

        post("/addArticle") {
            val formParameters = call.receiveParameters()
            val title = formParameters.getOrFail("title")
            val body = formParameters.getOrFail("body")
            articleDAO.addNewArticle(title, body)?.let {
                call.respond(it)
            } ?: run {
                call.respond("Something went wrong")
            }
        }

        get("/articles") {
            val listOfArticles = articleDAO.allArticles()
            if(listOfArticles.isEmpty()){
                call.respond("no articles found")
            }else{
                call.respond(listOfArticles)
            }
        }
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
