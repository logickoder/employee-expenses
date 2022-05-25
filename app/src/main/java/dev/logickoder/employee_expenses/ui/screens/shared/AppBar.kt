package dev.logickoder.employee_expenses.ui.screens.shared

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.theme.Theme

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    contentPadding: Dp = dimensionResource(id = R.dimen.padding)
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = Theme.colors.onBackground,
            )
        },
        actions = {
            AppBarButton(text = stringResource(id = R.string.info))
            AppBarButton(text = stringResource(id = R.string.logout))
        },
        backgroundColor = Theme.colors.background,
    )
}

@Composable
fun AppBarButton(
    text: String,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = {},
        content = {
            Text(
                text = text.uppercase()
            )
        }
    )
}
