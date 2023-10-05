package backend.controller

import backend.model.Test
import backend.service.TestService
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Application.testModule(testService: TestService) {
    install(ContentNegotiation) {
        jackson {}
    }
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)
        }
    }
    routing {
        route("/tests") {
            getTest(testService)
            createTest(testService)
            updateTest(testService)
            deleteTest(testService)
        }
    }
}

fun Route.getTest(testService: TestService) {
    get("/{id}") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
        val test = testService.getTestById(id)
        if (test != null) {
            call.respond(test)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}

fun Route.createTest(testService: TestService) {
    post("/") {
        val test = call.receive<Test>()
        val createdTest = testService.createTest(test)
        call.respond(HttpStatusCode.Created, createdTest)
    }
}

fun Route.updateTest(testService: TestService) {
    put("/{id}") {
        val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
        val test = call.receive<Test>()
        val updatedTest = testService.updateTest(id, test)
        if (updatedTest != null) {
            call.respond(HttpStatusCode.OK, updatedTest)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}

fun Route.deleteTest(testService: TestService) {
    delete("/{id}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        val deleted = testService.deleteTestById(id)
        if (deleted) {
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}
