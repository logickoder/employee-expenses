package dev.logickoder.expense_manager.ui.screens.shared.filter_form

import dev.logickoder.expense_manager.data.model.DataRow
import dev.logickoder.expense_manager.ui.domain.FormState
import dev.logickoder.expense_manager.ui.domain.MutableObservableState
import dev.logickoder.expense_manager.utils.toText
import java.time.LocalDate


class FilterFormState : FormState<DataRow> {
    val from = MutableObservableState<LocalDate?, LocalDate?, String>(
        initial = null,
        update = { it: LocalDate?, _ -> it },
        output = { it.toText() }
    )

    val to = MutableObservableState<LocalDate?, LocalDate?, String>(
        initial = null,
        update = { it: LocalDate?, _ -> it },
        output = { it.toText() }
    )

    val min = MutableObservableState<String?, Float?, String>(
        initial = null,
        update = { amount, _ -> amount?.toFloatOrNull() },
        output = { if (it == null) "" else "%.2f".format(it) }
    )

    val max = MutableObservableState<String?, Float?, String>(
        initial = null,
        update = { amount, _ -> amount?.toFloatOrNull() },
        output = { if (it == null) "" else "%.2f".format(it) }
    )

    val merchant = MutableObservableState<String?, String?, String>(
        initial = null,
        update = { it, _ -> it },
        output = { it ?: "" }
    )

    val status = MutableObservableState<Pair<Boolean, String>?, String?, String>(
        initial = null,
        update = { status, _ ->
            if (status?.first == true) status.second else null
        },
        output = { it ?: "" }
    )

    override fun hasError() = false

    override fun clearErrors() {}

    override fun save(): DataRow? {
        TODO("Not yet implemented")
    }

    override fun clear() {
        from.emit(null)
        to.emit(null)
        max.emit(null)
        min.emit(null)
        merchant.emit(null)
        status.emit(null)
    }
}