package dev.logickoder.expensemanager.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.replace
import dev.logickoder.expensemanager.app.Navigation

class LoginRoute(
    buildContext: BuildContext,
    private val backStack: BackStack<Navigation.Route>,
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val model = remember {
            LoginScreenModel()
        }
        LoginScreen(
            model = model,
            navigateToNextScreen = {
                backStack.replace(Navigation.Route.Home)
            }
        )
    }
}