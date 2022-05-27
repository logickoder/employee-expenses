package dev.logickoder.employee_expenses.ui.screens.shared

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.logickoder.employee_expenses.ui.theme.Theme

@Composable
fun AppBar(
    title: String,
    actions: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    navigateBack: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = navigateBack?.let { goBack ->
            {
                IconButton(
                    onClick = goBack,
                    content = {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null,
                        )
                    }
                )
            }
        },
        title = {
            Text(
                text = title,
                color = Theme.colors.onBackground,
            )
        },
        actions = {
            actions()
            Spacer(modifier = Modifier.width(12.dp))
        },
        backgroundColor = Theme.colors.background,
    )
}

@Composable
fun AppBarButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Theme.colors.primary.copy(alpha = 0.4f),
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
        content = {
            Text(
                text = text.uppercase()
            )
        }
    )
}
