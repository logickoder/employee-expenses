package dev.logickoder.employee_expenses

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dev.logickoder.employee_expenses.ui.Navigation
import dev.logickoder.employee_expenses.ui.logout
import dev.logickoder.employee_expenses.ui.screens.home.HomeState
import dev.logickoder.employee_expenses.ui.screens.login.LoginState
import dev.logickoder.employee_expenses.ui.screens.profile.ProfileState

class MainViewModel : ViewModel() {
    private var initialized = false

    lateinit var loginState: LoginState
        private set

    lateinit var profileState: ProfileState
        private set

    lateinit var homeState: HomeState
        private set

    fun initStates(navController: NavController) {
        if (!initialized) {
            loginState = LoginState(
                navigateToMainScreen = {
                    navController.navigate(
                        route = Navigation.Main.route,
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
            )
            profileState = ProfileState(
                goBack = {
                    navController.popBackStack()
                },
                logout = {
                    logout(navController)
                }
            )
            homeState = HomeState(
                navigateToProfileScreen = {
                    navController.navigate(Navigation.Main.Profile.route)
                },
                logout = {
                    logout(navController)
                }
            )
            initialized = true
        }
    }
}
