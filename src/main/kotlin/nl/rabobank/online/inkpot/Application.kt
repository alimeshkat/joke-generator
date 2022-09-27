package nl.rabobank.online.inkpot

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import nl.rabobank.online.inkpot.plugins.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}
