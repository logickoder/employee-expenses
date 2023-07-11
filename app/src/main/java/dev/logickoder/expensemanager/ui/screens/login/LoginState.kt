package dev.logickoder.expensemanager.ui.screens.login

import dev.logickoder.expensemanager.ui.domain.MutableObservableState

class LoginState {
    val username = MutableObservableState<String?, String?, String>(
        initial = null,
        update = { it, _ -> it },
        output = { it ?: "" }
    )

    val password = MutableObservableState<String?, String?, String>(
        initial = null,
        update = { it, _ -> it },
        output = { it ?: "" }
    )
}
