package dev.logickoder.employee_expenses.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import dev.logickoder.employee_expenses.R

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
) {
    val padding = dimensionResource(id = R.dimen.padding)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            padding - padding / 4, Alignment.CenterVertically
        ),
        content = {
            var username by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            LoginInput(
                text = R.string.username,
                value = username,
                onValueChanged = { name ->
                    username = name.replace(Regex("\\s+"), "")
                }
            )
            LoginInput(
                text = R.string.password,
                value = password,
                onValueChanged = { value ->
                    password = value
                },
                type = LoginInputType.Password,
            )
        }
    )
}
