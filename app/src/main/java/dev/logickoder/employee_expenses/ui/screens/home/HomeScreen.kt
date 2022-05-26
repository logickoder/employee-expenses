package dev.logickoder.employee_expenses.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.screens.shared.AppBar
import dev.logickoder.employee_expenses.ui.theme.Theme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    val horizontalPadding = dimensionResource(id = R.dimen.padding).let { padding ->
        padding - padding / 4
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                AppBar(contentPadding = horizontalPadding)
                HomeHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontalPadding),
                    reimbursed = 1000f,
                )
                HomeFilterForm(
                    state = rememberHomeFilterFormState()
                )
            }
        },
        backgroundColor = Theme.colors.surface,
        content = {
            
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null
                    )
                }
            )
        }
    )
}
