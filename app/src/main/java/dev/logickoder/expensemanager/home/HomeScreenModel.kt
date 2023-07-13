package dev.logickoder.expensemanager.home

import dev.logickoder.expensemanager.app.data.model.DataHeader
import dev.logickoder.expensemanager.app.data.model.DataRow
import dev.logickoder.expensemanager.app.data.repository.DataRepository
import dev.logickoder.expensemanager.app.state.MutableObservableState
import dev.logickoder.expensemanager.app.widgets.expenseform.ExpenseFormModel
import dev.logickoder.expensemanager.app.widgets.filterform.FilterFormModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val repository: DataRepository,
    private val scope: CoroutineScope,
) {
    val reimbursed: Flow<Float> = repository.data.map { list ->
        list.filter {
            it.status.equals("reimbursed", ignoreCase = true)
        }.map { it.total }.reduceOrNull { acc, current -> acc + current } ?: 0f
    }.flowOn(Dispatchers.Default)

    val headers = repository.headers

    val data = repository.data

    val expense = MutableObservableState(
        initial = ExpenseFormModel(),
        update = { state: ExpenseFormModel, _ -> state },
        output = { it }
    )

    val filterFormModel = FilterFormModel(repository, scope)

    fun onHeaderClicked(header: DataHeader) {
        scope.launch {
            repository.sort(header)
        }
    }

    fun onRowClick(row: DataRow?, onSuccess: suspend () -> Unit) {
        expense.emit(ExpenseFormModel(row))
        scope.launch {
            onSuccess()
        }
    }

    fun save(onSuccess: () -> Unit) {
        val model = expense.value.save() ?: return
        scope.launch {
            repository.add(model)
            expense.value.clear()
            onSuccess()
        }
    }
}