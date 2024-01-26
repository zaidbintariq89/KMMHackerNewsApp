import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class UserRequest(
    val username: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val userRole: String
)

@Serializable
data class ServiceRequest(
    val id: Int? = null,
    val name: String,
    val description: String
)