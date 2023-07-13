package dev.logickoder.expensemanager.app

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackSlider
import dev.logickoder.expensemanager.home.HomeRoute
import dev.logickoder.expensemanager.login.LoginRoute
import dev.logickoder.expensemanager.profile.ProfileRoute
import kotlinx.parcelize.Parcelize

class Navigation(
    buildContext: BuildContext,
    startingRoute: Route,
    private val backStack: BackStack<Route> = BackStack(
        initialElement = startingRoute,
        savedStateMap = buildContext.savedStateMap,
    ),
) : ParentNode<Navigation.Route>(
    buildContext = buildContext,
    navModel = backStack,
) {

    @Composable
    override fun View(modifier: Modifier) {
        Children(
            modifier = modifier,
            navModel = backStack,
            transitionHandler = rememberBackstackSlider(),
        )
    }

    override fun resolve(navTarget: Route, buildContext: BuildContext): Node {
        return when (navTarget) {
            Route.Login -> LoginRoute(
                buildContext = buildContext,
                backStack = backStack,
            )

            Route.Home -> HomeRoute(
                buildContext = buildContext,
                backStack = backStack,
            )

            Route.Profile -> ProfileRoute(
                buildContext = buildContext,
                backStack = backStack,
            )
        }
    }

    sealed interface Route : Parcelable {

        @Parcelize
        object Login : Route

        @Parcelize
        object Home : Route

        @Parcelize
        object Profile : Route
    }
}