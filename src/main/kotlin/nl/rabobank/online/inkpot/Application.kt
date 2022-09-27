import io.ktor.client.*
import io.ktor.client.engine.cio.*

fun main() {
    val client = HttpClient(CIO)
}