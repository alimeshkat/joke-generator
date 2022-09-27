package nl.rabobank.online.inkpot

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.request.*
import nl.rabobank.online.inkpot.plugins.client

class TeamsHook {

    data class Joke(@JsonProperty(value = "text") val text: String)

    suspend fun publishJoke(jokeString: String) {
        client.post("https://raboweb.webhook.office.com/webhookb2/176d40fb-a85b-4356-8e19-214b1c895cc9@6e93a626-8aca-4dc1-9191-ce291b4b75a1/IncomingWebhook/0906991649b24c5489163f564dc5e725/340e03b3-3a05-49dd-a50d-e1c620a57683") {
            setBody(jacksonObjectMapper().writeValueAsString(Joke(jokeString)))
        }
    }
}