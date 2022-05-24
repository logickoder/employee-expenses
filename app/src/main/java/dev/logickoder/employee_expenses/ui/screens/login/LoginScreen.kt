package dev.logickoder.employee_expenses.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Password
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.theme.Theme

@Preview
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val padding = dimensionResource(id = R.dimen.padding)
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.spacedBy(padding, Alignment.CenterVertically),
        content = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = Theme.typography.h5,
            )
            Divider(
                modifier = Modifier.layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        constraints.copy(
                            maxWidth = constraints.maxWidth + (padding * 2).roundToPx()
                        )
                    )
                    layout(placeable.width, placeable.height) {
                        placeable.place(-padding.roundToPx(), 0)
                    }
                }
            )
            LoginInput(
                text = R.string.username,
                content = {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {}
                    )
                }
            )
            LoginInput(
                text = R.string.password,
                content = {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Password,
                                contentDescription = null
                            )
                        },
                        visualTransformation = PasswordVisualTransformation()
                    )
                }
            )
            Button(
                onClick = {},
                content = {
                    Text(
                        text = stringResource(id = R.string.login)
                    )
                }
            )
        }
    )
}