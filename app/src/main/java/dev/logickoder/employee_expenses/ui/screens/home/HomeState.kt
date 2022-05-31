package dev.logickoder.employee_expenses.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class HomeState(
    val navigateToProfileScreen: () -> Unit,
    val logout: () -> Unit,
) {
    val tableState = DataTableState()
    val filterState = HomeFilterFormState()
    var reimbursed by mutableStateOf(1000f)
}
