package nl.rabobank.online.inkpot

import io.ktor.server.cio.*
import io.ktor.server.engine.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.rabobank.online.inkpot.plugins.jokeRoute
import nl.rabobank.online.inkpot.plugins.randomJoke
import nl.rabobank.online.inkpot.plugins.toMsTeams

fun main(): Unit = runBlocking {

    launch {
        scheduledDaily {
            randomJoke()?.let {
                toMsTeams(it)
            }
        }
    }

    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        jokeRoute()
    }.start(wait = true)
}
