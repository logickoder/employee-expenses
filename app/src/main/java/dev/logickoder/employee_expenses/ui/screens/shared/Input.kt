package dev.logickoder.employee_expenses.ui.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import dev.logickoder.employee_expenses.ui.theme.secondaryPadding

private val DefaultColor = Color.DarkGray
private fun Color.content() = copy(alpha = .8f)

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
    color: Color = DefaultColor,
    icon: Pair<Alignment.Horizontal, ImageVector>? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val padding = secondaryPadding() / 2
    BasicTextField(
        modifier = modifier.background(
            color = color.copy(alpha = 0.1f),
            shape = Theme.shapes.medium,
        ).padding(padding),
        value = value,
        onValueChange = onValueChanged,
        textStyle = Theme.typography.body1.copy(
            color = color.content(),
        ),
        singleLine = singleLine,
        enabled = enabled,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                innerTextField = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(padding),
                        content = {
                            val textField = @Composable {
                                Box(
                                    modifier = Modifier.weight(1f),
                                    content = {
                                        innerTextField()
                                    }
                                )
                            }
                            val iconComposable = @Composable {
                                if (icon != null) Icon(
                                    imageVector = icon.second,
                                    contentDescription = null,
                                    tint = color.content(),
                                )
                            }

                            if (icon?.first == Alignment.Start) {
                                iconComposable()
                                textField()
                            } else {
                                textField()
                                iconComposable()
                            }
                        }
                    )
                },
                interactionSource = interactionSource,
            )
        }
    )
}

@Composable
fun InputWithField(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    color: Color = DefaultColor,
    icon: Pair<Alignment.Horizontal, ImageVector>? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit,
) {
    Input(
        modifier = modifier,
        title = title,
        color = color,
        content = { interactionSource ->
            InputField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChanged = onValueChanged,
                icon = icon,
                color = color,
                keyboardOptions = keyboardOptions,
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
            )
        }
    )
}

@Composable
fun Input(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = DefaultColor,
    content: @Composable (MutableInteractionSource) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(secondaryPadding() / 4),
        content = {
            val interactionSource = remember { MutableInteractionSource() }
            val focused by interactionSource.collectIsFocusedAsState()
            InputTitle(
                text = title,
                color = if (!focused) {
                    color.content().content()
                } else Theme.colors.primary.content(),
            )
            content(interactionSource)
        }
    )
}
