import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class Greeting {
    private val platform = getPlatform()
    private val client = HttpClient()

    suspend fun greeting(): String {
        val response = client.get("http://127.0.0.1:8080/")
        return response.bodyAsText()
    }

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}