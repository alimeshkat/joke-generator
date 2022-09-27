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
        get("/jokes/dev") {
            val response: DevJokes =
                getDevJokes()

            call.respond(response)
        }
    }
}

private suspend fun getDevJokes(): DevJokes {
    val response: DevJokes =
        client.get("https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single")
            .body()


    client.post("https://raboweb.webhook.office.com/webhookb2/176d40fb-a85b-4356-8e19-214b1c895cc9@6e93a626-8aca-4dc1-9191-ce291b4b75a1/IncomingWebhook/5d11bf8bf146483c84ace9974c7f5865/2da6094e-7349-475e-b1fa-6f99bb965e9e") {
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
