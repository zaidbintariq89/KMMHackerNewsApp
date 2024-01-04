import androidx.compose.ui.window.ComposeUIViewController
import com.mobilelive.looking4.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
