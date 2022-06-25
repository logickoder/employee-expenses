package dev.logickoder.expense_manager.ui.screens.home

import dev.logickoder.expense_manager.data.repository.DataRepository
import dev.logickoder.expense_manager.ui.domain.MutableObservableState
import dev.logickoder.expense_manager.ui.screens.shared.expense_form.ExpenseFormState
import dev.logickoder.expense_manager.ui.screens.shared.filter_form.FilterFormState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeState(
    val repository: DataRepository,
    val filterFormState: FilterFormState,
) {
    val reimbursed: Flow<Float>
        get() = repository.data.map { list ->
            list.filter {
                it.status.equals("reimbursed", ignoreCase = true)
            }.map { it.total }.reduceOrNull { acc, current -> acc + current } ?: 0f
        }

    val expense = MutableObservableState(
        initial = ExpenseFormState(),
        update = { state: ExpenseFormState, _ -> state },
        output = { it }
    )

    suspend fun save(): Boolean {
        val newExpense = expense.value.save() ?: return false
        expense.value.clear()
        repository.add(newExpense)
        return true
    }
}