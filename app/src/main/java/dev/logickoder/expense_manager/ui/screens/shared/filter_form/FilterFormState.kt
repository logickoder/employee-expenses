package dev.logickoder.expense_manager.ui.screens.shared.filter_form

import dev.logickoder.expense_manager.utils.toText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.time.LocalDate


class FilterFormState {
    private val _from = MutableStateFlow<LocalDate?>(null)
    val from: Flow<String> get() = _from.map { it.toText() }
    fun updateFromDate(date: LocalDate) {
        _from.tryEmit(date)
    }

    private val _to = MutableStateFlow<LocalDate?>(null)
    val to: Flow<String> get() = _to.map { it.toText() }
    fun updateToDate(date: LocalDate) {
        _to.tryEmit(date)
    }

    private val _min = MutableStateFlow(0f)
    val min: Flow<String> get() = _min.map { "%.2f".format(it) }
    fun updateMinAmount(amount: String) {
        amount.toFloatOrNull()?.let {
            _min.tryEmit(it)
        }
    }

    private val _max = MutableStateFlow<Float?>(null)
    val max: Flow<String> get() = _max.map { if (it != null) "%.2f".format(it) else "" }
    fun updateMaxAmount(amount: String) {
        amount.toFloatOrNull()?.let {
            _max.tryEmit(it)
        }
    }

    private val _merchant = MutableStateFlow<String?>(null)
    val merchant: Flow<String> get() = _merchant.map { it ?: "" }
    fun updateMerchant(merchant: String) {
        _merchant.tryEmit(merchant)
    }

    private val _status = MutableStateFlow<String?>(null)
    val status: Flow<String?> get() = _status
    fun updateStatus(status: String, isChecked: Boolean) {
        if (isChecked) _status.tryEmit(status)
    }
}