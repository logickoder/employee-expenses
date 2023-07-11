package dev.logickoder.expensemanager.ui.screens.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.logickoder.expensemanager.ui.theme.secondaryPadding


@Composable
fun CheckboxItem(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: CheckboxColors = CheckboxDefaults.colors(),
    text: @Composable () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(secondaryPadding() / 4),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = {
                onCheckedChange?.invoke(!checked)
            },
        ),
        content = {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                colors = colors
            )
            text()
        }
    )
}