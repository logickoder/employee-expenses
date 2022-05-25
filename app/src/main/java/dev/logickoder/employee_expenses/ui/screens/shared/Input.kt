package dev.logickoder.employee_expenses.ui.screens.shared

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.theme.Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    icon: Pair<Alignment.Horizontal, @Composable () -> Unit>? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        textStyle = Theme.typography.body1.copy(
            color = Color.White.copy(alpha = 0.8f),
        ),
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                enabled = true,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Theme.colors.primary.copy(alpha = 0.14f),
                ),
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                leadingIcon = if (icon?.first == Alignment.Start) {
                    {
                        icon.second()
                    }
                } else null,
                trailingIcon = if (icon?.first == Alignment.End) {
                    {
                        icon.second()
                    }
                } else null,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(
                    dimensionResource(id = R.dimen.padding) / 4
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
    icon: Pair<Alignment.Horizontal, @Composable () -> Unit>? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        content = {
            val interactionSource = remember { MutableInteractionSource() }
            val hovering by interactionSource.collectIsFocusedAsState()
            Text(
                text = title,
                style = Theme.typography.body2.copy(fontWeight = FontWeight.Medium),
                color = if (!hovering) {
                    Theme.colors.onSecondary
                } else Theme.colors.primary.copy(alpha = 0.8f),
            )
            InputField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChanged = onValueChanged,
                icon = icon,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
            )
        }
    )
}
