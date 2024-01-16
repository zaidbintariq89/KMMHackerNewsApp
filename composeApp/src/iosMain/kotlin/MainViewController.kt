import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import com.mobilelive.looking4app.ui.login.LoginScreen

fun MainViewController() = ComposeUIViewController { Navigator(LoginScreen()) }
