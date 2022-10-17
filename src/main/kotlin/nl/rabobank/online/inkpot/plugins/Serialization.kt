package nl.rabobank.online.inkpot.plugins

import com.fasterxml.jackson.databind.SerializationFeature
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
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
    val webhook = "<REPLACE_ME>"
    routing {
        get("/jokes/dev") {
            val response: Gioke = client.get("https://v2.jokeapi.dev/joke/Any?" +
                    "blacklistFlags=nsfw,religious,political,racist,sexist,explicit")
                .body()
            val body = TeamsBody("$response")
            client.post(webhook){
                setBody(Gson().toJson(body))
            }
            call.respond(response)
        }
    }
}

data class Gioke(
    val error: Boolean,
    val category: String,
    val type: String,
    val setup: String,
    val delivery: String,
    val flags: Object?,
    val id: Int,
    val safe: Boolean,
    val lang: String
){
    override fun toString(): String {
        return "$setup $delivery"
    }
}

data class TeamsBody(@SerializedName("text") val body: String)
