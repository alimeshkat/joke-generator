package nl.rabobank.online.inkpot.plugins

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import nl.rabobank.online.inkpot.entities.Chuck

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/joke") {

            val response: Chuck = client.get("https://api.chucknorris.io/jokes/random").body()
            call.respond(response)

        }

        post("/posttest") {
           client.post("<REPACE_ME>"){
               setBody("{\"Text\": \"Lekker brood bakken\"}")
           }
        }
    }
}
