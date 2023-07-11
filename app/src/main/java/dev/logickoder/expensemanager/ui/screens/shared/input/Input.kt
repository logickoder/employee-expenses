package dev.logickoder.expensemanager.ui.screens.shared.input

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.logickoder.expensemanager.ui.theme.ErrorText
import dev.logickoder.expensemanager.ui.theme.primaryPadding
import dev.logickoder.expensemanager.ui.theme.secondaryPadding

@Composable
fun InputTitle(
    text: String,
    modifier: Modifier = Modifier,
    error: Boolean = false,
    required: Boolean = false,
    color: Color = MaterialTheme.colorScheme.onSecondary,
) {
    val contentColor = if (error) MaterialTheme.colorScheme.error else color
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(secondaryPadding() / 4),
        content = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = contentColor
                )
            )
            if (required) Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(contentColor, CircleShape)
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
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
                        color = (if (error == null) color else MaterialTheme.colorScheme.error).copy(
                            alpha = 0.1f
                        ),
                        shape = MaterialTheme.shapes.medium,
                    ),
                value = value,
                onValueChange = onValueChanged,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = color.content(),
                ),
                singleLine = singleLine,
                enabled = enabled,
                readOnly = readOnly,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                keyboardOptions = keyboardOptions,
                decorationBox = { innerTextField ->
                    TextFieldDefaults.DecorationBox(
                        value = value,
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
                        enabled = enabled,
                        singleLine = singleLine,
                        visualTransformation = visualTransformation,
                        interactionSource = interactionSource,
                        isError = error != null,
                        contentPadding = PaddingValues(primaryPadding() / 4),
                    )
                }
            )
            if (error != null) ErrorText(error = error)
        }
    )
}

@Composable
fun Input(
    title: String,
    state: InputState,
    modifier: Modifier = Modifier,
    content: @Composable (MutableInteractionSource, InputState) -> Unit = { interactionSource, inputState ->
        InputField(
            modifier = Modifier.fillMaxWidth(),
            state = inputState,
            interactionSource = interactionSource,
        )
    },
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(secondaryPadding() / 4),
        content = {
            val interactionSource = remember { MutableInteractionSource() }
            val focused by interactionSource.collectIsFocusedAsState()
            InputTitle(
                text = title,
                error = state.error != null,
                required = state.required,
                color = if (!focused) {
                    state.color.content().content()
                } else MaterialTheme.colorScheme.primary.content(),
            )
            content(interactionSource, state)
        }
    )
}
