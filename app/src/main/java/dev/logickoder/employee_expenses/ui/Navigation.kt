package dev.logickoder.employee_expenses.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.logickoder.employee_expenses.MainViewModel
import dev.logickoder.employee_expenses.ui.screens.home.HomeScreen
import dev.logickoder.employee_expenses.ui.screens.login.LoginScreen
import dev.logickoder.employee_expenses.ui.screens.profile.ProfileScreen

sealed class Navigation(
    val route: String,
) {
    object Login : Navigation("/login")
    object Main : Navigation("/") {
        object Home : Navigation("/home")
        object Profile : Navigation("/profile")
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    NavHost(
        navController = navController,
        startDestination = Navigation.Login.route,
        modifier = modifier,
        builder = {
            // initialized the states
            viewModel.initStates(navController)
            loginGraph(viewModel)
            mainGraph(viewModel)
        }
    )
}

fun NavGraphBuilder.loginGraph(
    viewModel: MainViewModel,
) {
    composable(
        route = Navigation.Login.route,
        content = {
            LoginScreen(
                state = viewModel.loginState,
            )
        }
    )
}

fun NavGraphBuilder.mainGraph(
    viewModel: MainViewModel,
) {
    navigation(
        startDestination = Navigation.Main.Home.route,
        route = Navigation.Main.route,
        builder = {
            composable(
                route = Navigation.Main.Home.route,
                content = {
                    HomeScreen(
                        state = viewModel.homeState
                    )
                }
            )
            composable(
                route = Navigation.Main.Profile.route,
                content = {
                    ProfileScreen(
                        profileState = viewModel.profileState,
                    )
                }
            )
        }
    )
}

fun logout(navController: NavController) {
    navController.navigate(
        route = Navigation.Login.route,
        builder = {
            popUpTo(
                route = Navigation.Main.route,
                popUpToBuilder = {
                    inclusive = true
                }
            )
        }
    )
}
