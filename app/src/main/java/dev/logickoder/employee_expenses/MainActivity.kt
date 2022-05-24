package dev.logickoder.employee_expenses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.logickoder.employee_expenses.ui.screens.login.LoginScreen
import dev.logickoder.employee_expenses.ui.theme.EmployeeExpensesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeExpensesTheme {
                LoginScreen()
            }
        }
    }
}
