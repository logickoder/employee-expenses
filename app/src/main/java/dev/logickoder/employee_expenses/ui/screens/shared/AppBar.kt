package dev.logickoder.employee_expenses.ui.screens.shared

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import dev.logickoder.employee_expenses.R

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    contentPadding: Dp = dimensionResource(id = R.dimen.padding).let { padding ->
        padding - padding / 4
    }
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
            )
        }
    )
}
