package dev.logickoder.employee_expenses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import dev.logickoder.employee_expenses.ui.NavGraph
import dev.logickoder.employee_expenses.ui.theme.EmployeeExpensesTheme
import dev.logickoder.employee_expenses.ui.theme.Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeExpensesTheme {
                val navController = rememberNavController()
                Surface(
                    color = Theme.colors.background,
                    content = {
                        NavGraph(navController = navController)
                    }
                )
            }
        }
    }
}
