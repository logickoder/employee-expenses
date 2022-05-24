package dev.logickoder.employee_expenses.ui.screens.login

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource


@Composable
fun LoginInput(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
        content = {
            Text(
                text = stringResource(id = text)
            )
            content()
        }
    )
}