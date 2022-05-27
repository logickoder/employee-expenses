package dev.logickoder.employee_expenses.ui.screens.profile

import androidx.compose.runtime.*

class ProfileState(
    val navigateToHomeScreen: () -> Unit,
    val navigateToLoginPage: () -> Unit,
) {
    var name by mutableStateOf("")
    var jobDescription by mutableStateOf("")
    var department by mutableStateOf("")
    var location by mutableStateOf("")
}

@Composable
fun rememberProfileState(
    navigateToHomeScreen: () -> Unit,
    logout: () -> Unit,
) = remember {
    ProfileState(navigateToHomeScreen, logout)
}
