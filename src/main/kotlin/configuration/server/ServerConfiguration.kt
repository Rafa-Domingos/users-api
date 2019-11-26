package configuration.server

import configuration.exceptions.exceptions
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.jackson.jackson
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import routing.userRoutes

fun ktorServer() = embeddedServer(Netty, port = 8080) {
    installExceptions()
    installRequestPayloadDeserializer()
    userRoutes()
}

fun Application.installRequestPayloadDeserializer() = install(ContentNegotiation) {
    jackson {  }
}

fun Application.installExceptions() = install(StatusPages) {
    exceptions()
}