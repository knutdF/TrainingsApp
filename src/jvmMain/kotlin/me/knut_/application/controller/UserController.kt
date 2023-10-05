package backend.controller

import backend.model.User
import backend.service.UserService
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Application.module(userService: UserService) {
    install(ContentNegotiation) {
        jackson {}
    }
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)
        }
    }
    routing {
        route("/users") {
            getUser(userService)
            createUser(userService)
            updateUser(userService)
            deleteUser(userService)
        }
    }
}

fun Route.getUser(userService: UserService) {
    get("/{id}") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
        val user = userService.getUserById(id)
        if (user != null) {
            call.respond(user)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}

fun Route.createUser(userService: UserService) {
    post("/") {
        val user = call.receive<User>()
        val createdUser = userService.createUser(user)
        call.respond(HttpStatusCode.Created, createdUser)
    }
}

fun Route.updateUser(userService: UserService) {
    put("/{id}") {
        val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
        val user = call.receive<User>()
        val updatedUser = userService.updateUser(id, user)
        if (updatedUser != null) {
            call.respond(HttpStatusCode.OK, updatedUser)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}

fun Route.deleteUser(userService: UserService) {
    delete("/{id}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        val deleted = userService.deleteUserById(id)
        if (deleted) {
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}
