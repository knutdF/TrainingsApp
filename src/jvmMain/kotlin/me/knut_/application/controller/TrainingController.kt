package backend.controller

import backend.model.Training
import backend.service.TrainingService
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Application.trainingModule(trainingService: TrainingService) {
    install(ContentNegotiation) {
        jackson {}
    }
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)
        }
    }
    routing {
        route("/trainings") {
            getTraining(trainingService)
            createTraining(trainingService)
            updateTraining(trainingService)
            deleteTraining(trainingService)
        }
    }
}

fun Route.getTraining(trainingService: TrainingService) {
    get("/{id}") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
        val training = trainingService.getTrainingById(id)
        if (training != null) {
            call.respond(training)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}

fun Route.createTraining(trainingService: TrainingService) {
    post("/") {
        val training = call.receive<Training>()
        val createdTraining = trainingService.createTraining(training)
        call.respond(HttpStatusCode.Created, createdTraining)
    }
}

fun Route.updateTraining(trainingService: TrainingService) {
    put("/{id}") {
        val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
        val training = call.receive<Training>()
        val updatedTraining = trainingService.updateTraining(id, training)
        if (updatedTraining != null) {
            call.respond(HttpStatusCode.OK, updatedTraining)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}

fun Route.deleteTraining(trainingService: TrainingService) {
    delete("/{id}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        val deleted = trainingService.deleteTrainingById(id)
        if (deleted) {
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}
