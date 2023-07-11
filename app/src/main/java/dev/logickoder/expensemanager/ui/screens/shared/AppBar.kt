package dev.logickoder.expensemanager.ui.screens.shared

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    modifier: Modifier = Modifier,
    actions: @Composable RowScope.() -> Unit = {},
    navigateBack: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            if (navigateBack != null) {
                IconButton(
                    onClick = navigateBack,
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
                color = MaterialTheme.colorScheme.onBackground,
            )
        },
        actions = {
            actions()
            Spacer(modifier = Modifier.width(12.dp))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
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
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
        ),
        contentPadding = PaddingValues(4.dp),
        shape = MaterialTheme.shapes.medium,
        content = {
            Text(
                text = text.uppercase(),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    )
}

@Composable
fun AppBarIconButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(CircleShape),
                imageVector = icon,
                contentDescription = null,
            )
        }
    )
}
