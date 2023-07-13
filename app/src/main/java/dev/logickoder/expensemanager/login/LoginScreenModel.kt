package dev.logickoder.expensemanager.login

import dev.logickoder.expensemanager.app.state.MutableObservableState

class LoginScreenModel {
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
