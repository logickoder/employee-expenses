package dev.logickoder.expense_manager.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import dev.logickoder.expense_manager.R
import dev.logickoder.expense_manager.ui.screens.shared.input.Input
import dev.logickoder.expense_manager.ui.screens.shared.input.InputState
import dev.logickoder.expense_manager.ui.theme.secondaryPadding
import dev.logickoder.expense_manager.utils.collectAsState

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    state: LoginState,
) = with(state) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            secondaryPadding(), Alignment.CenterVertically
        ),
        content = {
            Input(
                title = stringResource(id = R.string.username),
                state = InputState(
                    value = username.collectAsState().value,
                    color = Color.White,
                    onValueChanged = username::emit
                ),
            )
            Input(
                title = stringResource(id = R.string.password),
                state = InputState(
                    value = password.collectAsState().value,
                    color = Color.White,
                    onValueChanged = password::emit,
                    visualTransformation = PasswordVisualTransformation(),
                    icon = Alignment.End to Icons.Outlined.RemoveRedEye,
                )
            )
        }
    )
}
