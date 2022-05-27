package dev.logickoder.employee_expenses.ui.screens.login

import androidx.compose.runtime.*

class LoginState {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
}

@Composable
fun rememberLoginState(): LoginState {
    return remember { LoginState() }
}
