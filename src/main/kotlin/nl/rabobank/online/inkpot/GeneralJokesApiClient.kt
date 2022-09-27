package nl.rabobank.online.inkpot

import io.ktor.client.call.*
import io.ktor.client.request.*
import nl.rabobank.online.inkpot.plugins.client

class GeneralJokesApiClient: JokesApi<GeneralJokesApiClient.GeneralJoke> {

    class GeneralJoke: Joke() {
        var error: Boolean? = null
        var category: String? = null
        var type: String? = null
        var joke: String? = null
        var flags: Flag? = null
        var safe: Boolean? = true
        var id: Int? = null
        var lang: String? = null

        override fun getJokeAsString(): String? {
            return joke
        }
    }

    class Flag {
        var nsfw: Boolean? = false
        var religious: Boolean? = false
        var political: Boolean? = false
        var racist: Boolean? = false
        var sexist: Boolean? = false
        var explicit: Boolean? = false
    }

    override suspend fun getRandomJoke(): GeneralJoke {
        return client.get("https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&type=single").body()
    }
}