package dev.logickoder.expense_manager.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

typealias Theme = MaterialTheme

@Composable
fun EmployeeExpensesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = ColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}