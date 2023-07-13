package dev.logickoder.expensemanager.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.newRoot
import com.bumble.appyx.navmodel.backstack.operation.pop
import dev.logickoder.expensemanager.App
import dev.logickoder.expensemanager.app.Navigation
import dev.logickoder.expensemanager.app.data.repository.UserRepository

class ProfileRoute(
    buildContext: BuildContext,
    private val backStack: BackStack<Navigation.Route>,
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val app = (LocalContext.current.applicationContext as App)
        val scope = rememberCoroutineScope()
        val model = remember {
            ProfileScreenModel(
                repository = app[UserRepository::class.java],
                scope = scope,
            )
        }
        ProfileScreen(
            model = model,
            goBack = {
                backStack.pop()
            },
            logout = {
                backStack.newRoot(Navigation.Route.Login)
            }
        )
    }
}