package dev.logickoder.expensemanager.app.widgets.filterform

import dev.logickoder.expensemanager.app.data.repository.DataRepository
import dev.logickoder.expensemanager.app.state.FormModel
import dev.logickoder.expensemanager.app.state.MutableObservableState
import dev.logickoder.expensemanager.app.utils.float
import dev.logickoder.expensemanager.app.utils.formatted
import dev.logickoder.expensemanager.app.utils.toText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate


class FilterFormModel(
    private val repository: DataRepository,
    private val scope: CoroutineScope,
) : FormModel<Nothing>() {
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

    override fun save(): Nothing? {
        val query = StringBuilder(DataRepository.startingQuery)
        val startTime = from.value?.toEpochDay() ?: Long.MIN_VALUE
        val endTime = to.value?.toEpochDay() ?: Long.MAX_VALUE
        query.append(" WHERE date BETWEEN $startTime AND $endTime")
        val minAmount = min.value ?: Float.MIN_VALUE
        val maxAmount = max.value ?: Float.MAX_VALUE
        query.append(" AND total BETWEEN $minAmount AND $maxAmount")
        merchant.value?.let { query.append(" AND merchant = '$it'") }
        status.value?.let { query.append(" AND status = '$it'") }
        scope.launch {
            repository.query(query.toString())
        }
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