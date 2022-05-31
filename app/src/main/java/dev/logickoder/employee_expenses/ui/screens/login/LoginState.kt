package dev.logickoder.employee_expenses.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginState(
    val navigateToMainScreen: () -> Unit,
) {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
}
