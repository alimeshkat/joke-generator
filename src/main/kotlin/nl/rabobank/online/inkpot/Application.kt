package nl.rabobank.online.inkpot

import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.coroutines.runBlocking
import nl.rabobank.online.inkpot.plugins.configureRouting

fun main(): Unit = runBlocking {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)
}
