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
           client.post("https://raboweb.webhook.office.com/webhookb2/176d40fb-a85b-4356-8e19-214b1c895cc9@6e93a626-8aca-4dc1-9191-ce291b4b75a1/IncomingWebhook/0906991649b24c5489163f564dc5e725/2da6094e-7349-475e-b1fa-6f99bb965e9e"){
               setBody("{\"Text\": \"Lekker brood bakken\"}")
           }
        }
    }
}
