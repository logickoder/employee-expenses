package dev.logickoder.expense_manager.ui.screens.login

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class LoginState {
    private val _username = MutableStateFlow<String?>(null)
    val username: Flow<String> get() = _username.map { it ?: "" }
    fun updateUsername(username: String) {
        _username.tryEmit(username)
    }

    private val _password = MutableStateFlow<String?>(null)
    val password: Flow<String> get() = _password.map { it ?: "" }
    fun updatePassword(password: String) {
        _password.tryEmit(password)
    }
}
