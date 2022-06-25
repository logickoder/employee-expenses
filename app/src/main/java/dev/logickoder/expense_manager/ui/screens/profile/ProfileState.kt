package dev.logickoder.expense_manager.ui.screens.profile

import dev.logickoder.expense_manager.ui.domain.MutableObservableState

class ProfileState {
    val name = MutableObservableState<String, String?, String>(
        initial = null,
        update = { it, _ -> it },
        output = { it ?: "" }
    )

    val jobDescription = MutableObservableState<String, String?, String>(
        initial = null,
        update = { it, _ -> it },
        output = { it ?: "" }
    )

    val department = MutableObservableState<String, String?, String>(
        initial = null,
        update = { it, _ -> it },
        output = { it ?: "" }
    )

    val location = MutableObservableState<String, String?, String>(
        initial = null,
        update = { it, _ -> it },
        output = { it ?: "" }
    )

    val avatar = MutableObservableState<String, String?, String?>(
        initial = null,
        update = { it, _ -> it },
        output = { it }
    )

    val showGallery = MutableObservableState(
        initial = false,
        update = { show: Boolean, _ -> show },
        output = { it }
    )
}
