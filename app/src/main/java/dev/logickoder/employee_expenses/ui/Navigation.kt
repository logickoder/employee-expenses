package dev.logickoder.employee_expenses.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.logickoder.employee_expenses.ui.screens.home.HomeScreen
import dev.logickoder.employee_expenses.ui.screens.login.LoginScreen

sealed class Navigation(
    val route: String,
) {
    object Login : Navigation("/login")
    object Home : Navigation("/home")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.Login.route,
        modifier = modifier,
        builder = {
            loginGraph(navController)
            homeGraph(navController)
        }
    )
}

fun NavGraphBuilder.loginGraph(navController: NavHostController) {
    composable(
        route = Navigation.Login.route,
        content = {
            LoginScreen {
                navController.navigate(
                    route = Navigation.Home.route,
                    builder = {
                        popUpTo(
                            route = Navigation.Login.route,
                            popUpToBuilder = {
                                inclusive = true
                            }
                        )
                    }
                )
            }
        }
    )
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    composable(
        route = Navigation.Home.route,
        content = {
            HomeScreen()
        }
    )
}
