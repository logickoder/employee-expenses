package dev.logickoder.expensemanager.app.widgets.expenseform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.logickoder.expensemanager.R
import dev.logickoder.expensemanager.app.theme.ErrorText
import dev.logickoder.expensemanager.app.theme.secondaryPadding
import dev.logickoder.expensemanager.app.utils.collectAsState
import dev.logickoder.expensemanager.app.widgets.Avatar
import dev.logickoder.expensemanager.app.widgets.CheckboxItem
import dev.logickoder.expensemanager.app.widgets.DatePicker
import dev.logickoder.expensemanager.app.widgets.DropdownField
import dev.logickoder.expensemanager.app.widgets.ImageSelect
import dev.logickoder.expensemanager.ui.screens.shared.input.DefaultInputColor
import dev.logickoder.expensemanager.ui.screens.shared.input.Input
import dev.logickoder.expensemanager.ui.screens.shared.input.InputField
import dev.logickoder.expensemanager.ui.screens.shared.input.InputState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseForm(
    model: ExpenseFormModel,
    onSaveClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClicked: () -> Unit = {},
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            secondaryPadding(),
            Alignment.CenterVertically
        ),
        content = {
            Input(
                title = stringResource(id = R.string.merchant),
                state = InputState(
                    value = model.merchant.collectAsState().value,
                    readOnly = true,
                    required = true,
                    error = model.merchantError.collectAsState().value,
                ),
                content = { interactionSource, state ->
                    DropdownField(
                        suggested = state.value,
                        suggestions = stringArrayResource(id = R.array.merchants).toList(),
                        onSuggestionSelected = model.merchant::emit,
                        dropdownField = { _, expanded ->
                            InputField(
                                modifier = Modifier.menuAnchor(),
                                state = state.copy(
                                    icon = Alignment.End to if (expanded) {
                                        Icons.Outlined.ArrowDropUp
                                    } else Icons.Outlined.ArrowDropDown
                                ),
                                interactionSource = interactionSource,
                            )
                        }
                    )
                }
            )
            Input(
                title = stringResource(id = R.string.total),
                state = InputState(
                    value = model.total.collectAsState().value,
                    onValueChanged = model.total::emit,
                    icon = Alignment.Start to Icons.Outlined.AttachMoney,
                    error = model.totalError.collectAsState().value,
                    required = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    ),
                )
            )
            DatePicker(
                title = stringResource(id = R.string.date),
                state = InputState(
                    value = model.date.collectAsState().value,
                    error = model.dateError.collectAsState().value,
                    required = true,
                ),
                onDateChange = model.date::emit,
            )
            Input(
                title = stringResource(id = R.string.comment),
                state = InputState(
                    value = model.comment.collectAsState().value,
                    onValueChanged = model.comment::emit
                ),
                content = { interactionSource, state ->
                    InputField(
                        modifier = Modifier.height(56.dp * 4),
                        state = state,
                        interactionSource = interactionSource,
                    )
                }
            )
            Input(
                title = stringResource(id = R.string.status),
                state = InputState(model.status.collectAsState().value.orEmpty()),
                content = { interactionSource, state ->
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
                                                checked = state.value == item,
                                                onCheckedChange = { isChecked ->
                                                    model.status.emit(isChecked to item)
                                                },
                                                text = {
                                                    Text(item)
                                                }
                                            )
                                        }
                                    }
                                )
                            }
                            model.statusError.collectAsState().value?.let {
                                ErrorText(error = it)
                            }
                        }
                    )
                }
            )
            ImageSelect(
                onImageSelected = {
                    model.receipt.emit(it)
                },
                content = {

                    TextButton(
                        content = {
                            Text(stringResource(id = R.string.select_receipt))
                        },
                        onClick = it,
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = DefaultInputColor.copy(0.05f)
                        ),
                    )
                }
            )
            Avatar(
                model = model.receipt.collectAsState().value,
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
                            containerColor = DefaultInputColor.copy(0.05f)
                        ),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (!model.isEdit) TextButton(
                        content = {
                            Text(stringResource(id = R.string.delete))
                        },
                        onClick = onDeleteClicked,
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = DefaultInputColor.copy(0.05f),
                            contentColor = MaterialTheme.colorScheme.error,
                        ),
                    )
                }
            )
        }
    )
}