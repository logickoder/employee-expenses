package dev.logickoder.expense_manager

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import dev.logickoder.expense_manager.ui.NavGraph
import dev.logickoder.expense_manager.ui.theme.ExpenseManagerTheme
import dev.logickoder.expense_manager.ui.theme.Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseManagerTheme {
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
