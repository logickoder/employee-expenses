package dev.logickoder.employee_expenses.ui.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import dev.logickoder.employee_expenses.ui.theme.Theme
import dev.logickoder.employee_expenses.ui.theme.primaryPadding

@Composable
fun InputTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Theme.colors.onSecondary,
) {
    Text(
        modifier = modifier,
        text = text,
        style = Theme.typography.body2.copy(fontWeight = FontWeight.Medium),
        color = color,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    icon: Pair<Alignment.Horizontal, ImageVector>? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    BasicTextField(
        modifier = modifier.background(
            color = color.copy(alpha = 0.2f),
            shape = Theme.shapes.medium,
        ),
        value = value,
        onValueChange = onValueChanged,
        textStyle = Theme.typography.body1.copy(
            color = color.copy(alpha = 0.8f),
        ),
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                enabled = true,
                singleLine = true,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                leadingIcon = if (icon?.first == Alignment.Start) {
                    {
                        Icon(
                            imageVector = icon.second,
                            contentDescription = null,
                            tint = color.copy(alpha = 0.8f),
                        )
                    }
                } else null,
                trailingIcon = if (icon?.first == Alignment.End) {
                    {
                        Icon(
                            imageVector = icon.second,
                            contentDescription = null,
                            tint = color.copy(alpha = 0.8f),
                        )
                    }
                } else null,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(
                    primaryPadding() / 4
                )
            )
        }
    )
}

@Composable
fun Input(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    icon: Pair<Alignment.Horizontal, ImageVector>? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        content = {
            val interactionSource = remember { MutableInteractionSource() }
            val hovering by interactionSource.collectIsFocusedAsState()
            InputTitle(
                text = title,
                color = if (!hovering) {
                    color
                } else Theme.colors.primary.copy(alpha = 0.8f),
            )
            InputField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChanged = onValueChanged,
                icon = icon,
                color = color,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
            )
        }
    )
}
