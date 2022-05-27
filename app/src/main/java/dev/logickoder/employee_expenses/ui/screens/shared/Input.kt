package dev.logickoder.employee_expenses.ui.screens.shared

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import dev.logickoder.employee_expenses.ui.theme.Theme

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
    icon: Pair<Alignment.Horizontal, @Composable () -> Unit>? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        textStyle = Theme.typography.body1.copy(
            color = Color.White.copy(alpha = 0.8f),
        ),
        interactionSource = interactionSource,
        visualTransformation = visualTransformation,
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
        singleLine = true,
    )
//    BasicTextField(
//        modifier = modifier.background(
//            color = Color.Black.copy(alpha = 0.4f),
//            shape = Theme.shapes.medium,
//        ),
//        value = value,
//        onValueChange = onValueChanged,
//        textStyle = Theme.typography.body1.copy(
//            color = Color.White.copy(alpha = 0.8f),
//        ),
//        visualTransformation = visualTransformation,
//        interactionSource = interactionSource,
//        decorationBox = { innerTextField ->
//            TextFieldDefaults.TextFieldDecorationBox(
//                value = value,
//                enabled = true,
//                singleLine = true,
//                visualTransformation = visualTransformation,
//                innerTextField = innerTextField,
//                leadingIcon = if (icon?.first == Alignment.Start) {
//                    {
//                        icon.second()
//                    }
//                } else null,
//                trailingIcon = if (icon?.first == Alignment.End) {
//                    {
//                        icon.second()
//                    }
//                } else null,
//                interactionSource = interactionSource,
//                contentPadding = PaddingValues(
//                    dimensionResource(id = R.dimen.padding) / 4
//                )
//            )
//        }
//    )
}

@Composable
fun Input(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    titleColor: Color = Color.Black,
    icon: Pair<Alignment.Horizontal, @Composable () -> Unit>? = null,
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
                    titleColor
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
