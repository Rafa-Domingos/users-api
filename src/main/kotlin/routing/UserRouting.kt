package routing

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondTextWriter
import io.ktor.routing.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.reactive.collect
import org.litote.kmongo.json
import service.*

@ExperimentalCoroutinesApi
fun Application.userRoutes() {
    routing {
        get("teachers/{id}") {
            call.respond(HttpStatusCode.OK, getTeacherById(call.parameters["id"]!!))
        }

        get("students/{id}") {
            call.respond(HttpStatusCode.OK, getStudentById(call.parameters["id"]!!))
        }

        post("teachers") {
            call.response.let {
                it.headers.append("Location", "/${saveTeacher(call.receive())._id}", true)
                it.status(HttpStatusCode.Created)
            }
        }

        post("students") {
            call.response.let {
                it.headers.append("Location", "/${saveStudent(call.receive())._id}", true)
                it.status(HttpStatusCode.Created)
            }
        }

        delete("teachers/{id}") {
            delete(call.parameters["id"]!!)
            call.response.status(HttpStatusCode.NoContent)
        }

        delete("students/{id}") {
            delete(call.parameters["id"]!!)
            call.response.status(HttpStatusCode.NoContent)
        }

        put("students/{id}") {
            replace(call.parameters["id"]!!, call.receive())
            call.response.status(HttpStatusCode.NoContent)
        }

        put("teachers/{id}") {
            replace(call.parameters["id"]!!, call.receive())
            call.response.status(HttpStatusCode.NoContent)
        }
    }
}