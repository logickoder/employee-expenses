package dev.logickoder.expense_manager.ui.screens.shared.expense_form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.logickoder.expense_manager.R
import dev.logickoder.expense_manager.ui.screens.shared.*
import dev.logickoder.expense_manager.ui.screens.shared.input.*
import dev.logickoder.expense_manager.ui.theme.Theme
import dev.logickoder.expense_manager.ui.theme.secondaryPadding
import dev.logickoder.expense_manager.utils.collectAsState


@Composable
fun ExpenseForm(
    state: ExpenseFormState,
    onSaveClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClicked: () -> Unit = {},
) = with(state) {
    if (showGallery.collectAsState().value) {
        ImageSelect(
            onImageSelected = {
                receipt.emit(it)
                showGallery.emit(false)
            }
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            secondaryPadding(),
            Alignment.CenterVertically
        ),
        content = {
            Input(
                title = stringResource(id = R.string.merchant),
                content = { interactionSource ->
                    DropdownField(
                        suggested = merchant.collectAsState().value,
                        suggestions = stringArrayResource(id = R.array.merchants).toList(),
                        onSuggestionSelected = merchant::emit,
                        dropdownField = { suggested, expanded ->
                            InputField(
                                state = InputState(
                                    value = suggested,
                                    readOnly = true,
                                    error = merchantError.collectAsState().value,
                                    icon = Alignment.End to if (expanded) {
                                        Icons.Outlined.ArrowDropUp
                                    } else Icons.Outlined.ArrowDropDown,
                                ),
                                interactionSource = interactionSource,
                            )
                        }
                    )
                }
            )
            InputWithField(
                title = stringResource(id = R.string.total),
                state = InputState(
                    value = total.collectAsState().value,
                    onValueChanged = total::emit,
                    icon = Alignment.Start to Icons.Outlined.AttachMoney,
                    error = totalError.collectAsState().value,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    ),
                )
            )
            DatePicker(
                title = stringResource(id = R.string.date),
                state = InputState(
                    value = date.collectAsState().value,
                    error = dateError.collectAsState().value,
                ),
                onDateChange = date::emit,
            )
            Input(
                title = stringResource(id = R.string.comment),
                content = { interactionSource ->
                    InputField(
                        modifier = Modifier.height(56.dp * 4),
                        state = InputState(
                            value = comment.collectAsState().value,
                            onValueChanged = comment::emit
                        ),
                        interactionSource = interactionSource,
                    )
                }
            )
            Input(
                title = stringResource(id = R.string.status),
                content = { interactionSource ->
                    val statuses = stringArrayResource(id = R.array.statuses).toList()
                    Column(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = {
                                interactionSource.tryEmit(HoverInteraction.Enter())
                            }
                        ),
                        content = {
                            statuses.chunked(2).forEach { row ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(
                                        secondaryPadding() / 2
                                    ),
                                    content = {
                                        row.forEach { item ->
                                            CheckboxItem(
                                                checked = status.collectAsState().value == item,
                                                onCheckedChange = { isChecked ->
                                                    status.emit(isChecked to item)
                                                },
                                                text = {
                                                    Text(item)
                                                }
                                            )
                                        }
                                    }
                                )
                            }
                            statusError.collectAsState().value?.let {
                                Text(it, color = Theme.colors.error)
                            }
                        }
                    )
                }
            )
            TextButton(
                content = {
                    Text(stringResource(id = R.string.select_receipt))
                },
                onClick = {
                    showGallery.emit(true)
                },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = DefaultInputColor.copy(0.05f)
                ),
            )
            Avatar(
                model = receipt.collectAsState().value,
                shape = RoundedCornerShape(secondaryPadding()),
                size = 1f,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(secondaryPadding()),
                content = {
                    Button(
                        content = {
                            Text(stringResource(id = R.string.save))
                        },
                        onClick = onSaveClicked,
                    )
                    TextButton(
                        content = {
                            Text(stringResource(id = R.string.cancel))
                        },
                        onClick = onCancelClicked,
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = DefaultInputColor.copy(0.05f)
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (!state.isEdit) TextButton(
                        content = {
                            Text(stringResource(id = R.string.delete))
                        },
                        onClick = onDeleteClicked,
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = DefaultInputColor.copy(0.05f),
                            contentColor = Theme.colors.error,
                        ),
                    )
                }
            )
        }
    )
}