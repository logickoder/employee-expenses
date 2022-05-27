package dev.logickoder.employee_expenses.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.screens.shared.Input

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    loginState: LoginState,
) = with(loginState) {
    val padding = dimensionResource(id = R.dimen.padding)
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            padding - padding / 4, Alignment.CenterVertically
        ),
        content = {
            Input(
                title = stringResource(id = R.string.username),
                value = username,
                titleColor = Color.White,
                onValueChanged = { name ->
                    username = name
                }
            )
            Input(
                title = stringResource(id = R.string.password),
                value = password,
                titleColor = Color.White,
                onValueChanged = { value ->
                    password = value
                },
                visualTransformation = PasswordVisualTransformation(),
                icon = Alignment.End to {
                    Icon(
                        imageVector = Icons.Outlined.RemoveRedEye,
                        contentDescription = null
                    )
                }
            )
        }
    )
}
