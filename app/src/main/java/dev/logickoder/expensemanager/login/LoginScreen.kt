package dev.logickoder.expensemanager.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.logickoder.expensemanager.R

@Composable
fun LoginScreen(
    model: LoginScreenModel,
    modifier: Modifier = Modifier,
    navigateToNextScreen: () -> Unit,
    contentPadding: Dp = dimensionResource(id = R.dimen.padding),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding + contentPadding / 4),
        verticalArrangement = Arrangement.spacedBy(contentPadding, Alignment.CenterVertically),
        content = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onBackground,
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
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            )
            LoginForm(model = model)
            Button(
                onClick = navigateToNextScreen,
                content = {
                    Text(
                        text = stringResource(id = R.string.login)
                    )
                }
            )
        }
    )
}
