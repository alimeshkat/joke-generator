package nl.rabobank.online.inkpot.plugins

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*


val client = HttpClient(CIO){
    install(ContentNegotiation) {
        jackson()
    }
}
