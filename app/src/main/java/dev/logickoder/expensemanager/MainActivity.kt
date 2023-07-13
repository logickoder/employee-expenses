package dev.logickoder.expensemanager

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity
import dev.logickoder.expensemanager.app.Navigation
import dev.logickoder.expensemanager.app.theme.ExpenseManagerTheme

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseManagerTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    content = {
                        NodeHost(
                            integrationPoint = appyxIntegrationPoint,
                            factory = {
                                Navigation(
                                    buildContext = it,
                                    startingRoute = Navigation.Route.Login,
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}
