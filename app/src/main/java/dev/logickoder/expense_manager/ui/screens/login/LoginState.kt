package dev.logickoder.expense_manager.ui.screens.login

import dev.logickoder.expense_manager.ui.domain.MutableObservableState

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
