package dev.logickoder.expense_manager.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import dev.logickoder.expense_manager.R
import dev.logickoder.expense_manager.ui.screens.shared.input.InputState
import dev.logickoder.expense_manager.ui.screens.shared.input.InputWithField

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    state: LoginState,
) {
    val padding = dimensionResource(id = R.dimen.padding)
    val username by state.username.collectAsState(initial = "")
    val password by state.password.collectAsState(initial = "")

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            padding - padding / 4, Alignment.CenterVertically
        ),
        content = {
            InputWithField(
                title = stringResource(id = R.string.username),
                state = InputState(
                    value = username,
                    color = Color.White,
                    onValueChanged = state::updateUsername
                ),
            )
            InputWithField(
                title = stringResource(id = R.string.password),
                state = InputState(
                    value = password,
                    color = Color.White,
                    onValueChanged = state::updatePassword,
                    visualTransformation = PasswordVisualTransformation(),
                    icon = Alignment.End to Icons.Outlined.RemoveRedEye,
                )
            )
        }
    )
}
