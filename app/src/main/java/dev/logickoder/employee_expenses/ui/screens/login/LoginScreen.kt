package dev.logickoder.employee_expenses.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.theme.Theme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    contentPadding: Dp = dimensionResource(id = R.dimen.padding),
    navigateToHomeScreen: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding + contentPadding / 4),
        verticalArrangement = Arrangement.spacedBy(contentPadding, Alignment.CenterVertically),
        content = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = Theme.typography.h5.copy(fontWeight = FontWeight.SemiBold),
                color = Theme.colors.onBackground,
            )
            Divider(
                modifier = Modifier.layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        constraints.copy(
                            maxWidth = constraints.maxWidth + (contentPadding * 2).roundToPx()
                        )
                    )
                    layout(placeable.width, placeable.height) {
                        placeable.place(0, 0)
                    }
                },
                thickness = 4.dp,
                color = Theme.colors.primary.copy(alpha = 0.5f)
            )
            LoginForm(
                loginState = rememberLoginState(scope = rememberCoroutineScope())
            )
            Button(
                onClick = navigateToHomeScreen,
                content = {
                    Text(
                        text = stringResource(id = R.string.login)
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        navigateToHomeScreen = {}
    )
}
