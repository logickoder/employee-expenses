package dev.logickoder.expense_manager.ui.screens.shared.input

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.logickoder.expense_manager.ui.theme.Theme
import dev.logickoder.expense_manager.ui.theme.primaryPadding
import dev.logickoder.expense_manager.ui.theme.secondaryPadding

@Composable
fun InputTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Theme.colors.onSecondary,
) {
    Row(
        content = {
            Text(
                modifier = modifier,
                text = text,
                style = Theme.typography.body2.copy(fontWeight = FontWeight.Medium),
                color = color,
            )
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputField(
    state: InputState,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) = with(state) {
    Column(
        content = {
            BasicTextField(
                modifier = modifier
                    .background(
                        color = color.copy(alpha = 0.1f),
                        shape = Theme.shapes.medium,
                    ),
                value = value,
                onValueChange = onValueChanged,
                textStyle = Theme.typography.body1.copy(
                    color = color.content(),
                ),
                singleLine = singleLine,
                enabled = enabled,
                readOnly = readOnly,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                keyboardOptions = keyboardOptions,
                decorationBox = { innerTextField ->
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = value,
                        enabled = enabled,
                        singleLine = singleLine,
                        visualTransformation = visualTransformation,
                        isError = error != null,
                        innerTextField = {
                            val padding = 2.dp
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
                                            modifier = Modifier.size(20.dp),
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
                        contentPadding = PaddingValues(primaryPadding() / 4),
                        interactionSource = interactionSource,
                    )
                }
            )
            if (error != null) {
                Text(
                    error,
                    color = Theme.colors.error,
                )
            }
        }
    )
}

@Composable
fun InputWithField(
    title: String,
    state: InputState,
    modifier: Modifier = Modifier,
) {
    Input(
        modifier = modifier,
        title = title,
        color = state.color,
        content = { interactionSource ->
            InputField(
                modifier = Modifier.fillMaxWidth(),
                state = state,
                interactionSource = interactionSource,
            )
        }
    )
}

@Composable
fun Input(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = DefaultInputColor,
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
