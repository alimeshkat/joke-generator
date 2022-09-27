package nl.rabobank.online.inkpot

interface JokesApi<T: Joke> {

    suspend fun getRandomJoke(): T
}