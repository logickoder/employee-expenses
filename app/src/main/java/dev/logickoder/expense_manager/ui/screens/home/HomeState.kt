package dev.logickoder.expense_manager.ui.screens.home

import dev.logickoder.expense_manager.data.repository.DataRepository
import dev.logickoder.expense_manager.ui.domain.MutableObservableState
import dev.logickoder.expense_manager.ui.domain.ObservableState
import dev.logickoder.expense_manager.ui.screens.shared.expense_form.ExpenseFormState
import dev.logickoder.expense_manager.ui.screens.shared.filter_form.FilterFormState

class HomeState(
    val repository: DataRepository,
    val filterFormState: FilterFormState,
) {
    val reimbursed = ObservableState(
        initial = 1000f,
        output = { it }
    )

    val expense = MutableObservableState(
        initial = ExpenseFormState(),
        update = { state: ExpenseFormState, _ -> state },
        output = { it }
    )

    fun save(): Boolean {
        val expense = expense.value.save() ?: return false
        repository.add(expense)
        return true
    }
}