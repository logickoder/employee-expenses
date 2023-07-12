package dev.logickoder.expensemanager.ui.screens.shared.filter_form

import dev.logickoder.expensemanager.app.data.repository.DataRepository
import dev.logickoder.expensemanager.app.state.FormState
import dev.logickoder.expensemanager.app.state.MutableObservableState
import dev.logickoder.expensemanager.app.utils.float
import dev.logickoder.expensemanager.app.utils.formatted
import dev.logickoder.expensemanager.app.utils.toText
import java.time.LocalDate


class FilterFormState(
    private val repository: DataRepository,
) : FormState<Nothing>() {
    val from = MutableObservableState<LocalDate?, LocalDate?, String>(
        initial = null,
        update = { it, _ -> it },
        output = { it?.toText() ?: "" }
    )

    val to = MutableObservableState<LocalDate?, LocalDate?, String>(
        initial = null,
        update = { it, _ -> it },
        output = { it?.toText() ?: "" }
    )

    val min = MutableObservableState<String?, Float?, String>(
        initial = null,
        update = { amount, _ -> amount?.float },
        output = { it?.formatted ?: "" }
    )

    val max = MutableObservableState<String?, Float?, String>(
        initial = null,
        update = { amount, _ -> amount?.float },
        output = { it?.formatted ?: "" }
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

    override suspend fun save(): Nothing? {
        val query = StringBuilder(DataRepository.startingQuery)
        val startTime = from.value?.toEpochDay() ?: Long.MIN_VALUE
        val endTime = to.value?.toEpochDay() ?: Long.MAX_VALUE
        query.append(" WHERE date BETWEEN $startTime AND $endTime")
        val minAmount = min.value ?: Float.MIN_VALUE
        val maxAmount = max.value ?: Float.MAX_VALUE
        query.append(" AND total BETWEEN $minAmount AND $maxAmount")
        merchant.value?.let { query.append(" AND merchant = '$it'") }
        status.value?.let { query.append(" AND status = '$it'") }
        repository.query(query.toString())
        return null
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