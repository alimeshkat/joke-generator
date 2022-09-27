package nl.rabobank.online.inkpot

import com.fasterxml.jackson.annotation.JsonProperty
import io.ktor.client.call.*
import io.ktor.client.request.*
import nl.rabobank.online.inkpot.plugins.client

class ChuckJokesClient: JokesApi<ChuckJokesClient.ChuckJoke> {

    class ChuckJoke: Joke() {
        var categories: MutableSet<String> = mutableSetOf()
        var created_at: String? = null
        var icon_url: String? = null
        var id: String? = null
        var updated_at: String? = null
        @JsonProperty(value = "value") var joke: String? = null
        var url: String? = null

        override fun getJokeAsString(): String? {
          return joke
        }
    }

    override suspend fun getRandomJoke(): ChuckJoke {
        return client.get("https://api.chucknorris.io/jokes/random?category=animal,career,celebrity,dev,fashion,food,history,money,movie,music,science,sport,travel").body()
    }
}