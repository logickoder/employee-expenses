package dev.logickoder.employee_expenses.ui.screens.login

import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import dev.logickoder.employee_expenses.ui.theme.Theme

enum class LoginInputType {
    Normal,
    Password
}

@Composable
fun LoginInput(
    @StringRes text: Int,
    value: String,
    modifier: Modifier = Modifier,
    type: LoginInputType = LoginInputType.Normal,
    onValueChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        content = {
            val interactionSource = remember { MutableInteractionSource() }
            val hovering by interactionSource.collectIsFocusedAsState()
            Text(
                text = stringResource(id = text),
                style = Theme.typography.body2,
                color = if (!hovering) {
                    Theme.colors.onSecondary
                } else Theme.colors.primary.copy(alpha = 0.8f),
            )

            val password = type == LoginInputType.Password
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChanged,
                shape = Theme.shapes.medium,
                trailingIcon = if (password) {
                    {
                        Icon(
                            imageVector = Icons.Outlined.Password,
                            contentDescription = null
                        )
                    }
                } else null,
                visualTransformation = if (password) {
                    PasswordVisualTransformation()
                } else VisualTransformation.None,
                interactionSource = interactionSource
            )
        }
    )
}
