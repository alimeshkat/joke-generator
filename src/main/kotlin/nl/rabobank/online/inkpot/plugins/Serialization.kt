package nl.rabobank.online.inkpot.plugins

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        get("/json/jackson") {
            val response: DadJoke = client.get("https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit").body()
            call.respond(response)
        }
    }
}

data class DadJoke(
    val categories: List<String>,
    val created_at: String,
    val updated_at: String,
    val icon_url: String,
    val id: String,
    val url: String,
    val value: String
)
