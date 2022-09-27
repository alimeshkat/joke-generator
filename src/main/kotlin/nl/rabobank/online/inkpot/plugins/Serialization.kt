package nl.rabobank.online.inkpot.plugins

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        post("/jokes/dev") {
            val response: DevJokes =
                getDevJokes()

            call.respond(response)
        }
    }
}

suspend fun getDevJokes(): DevJokes {
    val response: DevJokes =
        client.get("https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single")
            .body()


    client.post("<REPLACE_ME>") {
        setBody(ObjectMapper().writeValueAsString(TeamsDTO(response.joke)))
    }
    return response
}

data class DevJokes(
    val error: String,
    val category: String,
    val type: String,
    val joke: String,
    val flags: Flags,
    val id: Int,
    val safe: Boolean,
    val lang: String
)

data class Flags(
    val nsfw: Boolean,
    val religious: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val sexist: Boolean,
    val explicit: Boolean
)

data class TeamsDTO(@field:JsonProperty("Text") val text: String)
