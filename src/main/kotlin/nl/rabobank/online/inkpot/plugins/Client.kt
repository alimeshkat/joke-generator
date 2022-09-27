package nl.rabobank.online.inkpot.plugins

import com.fasterxml.jackson.databind.util.Converter
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.jackson.*
import io.ktor.client.plugins.logging.*


val client = HttpClient(CIO){
    install(ContentNegotiation) {
        jackson()
    }
    install(Logging) {
        level = LogLevel.BODY
    }
}