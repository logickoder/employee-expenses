package dev.logickoder.employee_expenses.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.logickoder.employee_expenses.ui.screens.home.HomeScreen
import dev.logickoder.employee_expenses.ui.screens.login.LoginScreen
import dev.logickoder.employee_expenses.ui.screens.profile.ProfileScreen
import dev.logickoder.employee_expenses.ui.screens.profile.rememberProfileState

sealed class Navigation(
    val route: String,
) {
    object Login : Navigation("/login")
    object Home : Navigation("/home")
    object Profile : Navigation("/profile")
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
            profileGraph(navController)
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

fun NavGraphBuilder.homeGraph(
    navController: NavHostController
) {
    composable(
        route = Navigation.Home.route,
        content = {
            HomeScreen(
                navigateToProfileScreen = {
                    navController.navigate(Navigation.Profile.route)
                },
                logout = { logout(navController) }
            )
        }
    )
}

fun NavGraphBuilder.profileGraph(
    navController: NavHostController
) {
    composable(
        route = Navigation.Profile.route,
        content = {
            ProfileScreen(
                profileState = rememberProfileState(
                    navigateToHomeScreen = {
                        navController.popBackStack()
                    },
                    logout = { logout(navController) }
                )
            )
        }
    )
}

private fun logout(navController: NavController) {
    navController.navigate(
        route = Navigation.Login.route,
        builder = {
            popUpTo(
                route = Navigation.Home.route,
                popUpToBuilder = {
                    inclusive = true
                }
            )
        }
    )
}
