package dev.logickoder.employee_expenses.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginState(
    private val scope: CoroutineScope
) {
    private val _username = MutableStateFlow("")
    val username: Flow<String>
        get() = _username

    private val _password = MutableStateFlow("")
    val password: Flow<String>
        get() = _password

    fun updateUsername(value: String) {
        scope.launch {
            _username.emit(value.replace(Regex("\\s+"), ""))
        }
    }

    fun updatePassword(value: String) {
        scope.launch {
            _password.emit(value)
        }
    }
}

@Composable
fun rememberLoginState(scope: CoroutineScope): LoginState {
    return remember { LoginState(scope) }
}
