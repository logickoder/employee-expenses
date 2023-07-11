package dev.logickoder.expensemanager

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import dev.logickoder.expensemanager.ui.NavGraph
import dev.logickoder.expensemanager.ui.theme.ExpenseManagerTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseManagerTheme {
                val navController = rememberNavController()
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    content = {
                        NavGraph(navController = navController)
                    }
                )
            }
        }
    }
}
