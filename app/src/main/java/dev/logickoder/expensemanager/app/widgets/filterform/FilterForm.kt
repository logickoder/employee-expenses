package dev.logickoder.expensemanager.app.widgets.filterform

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.Dp
import dev.logickoder.expensemanager.R
import dev.logickoder.expensemanager.app.theme.secondaryPadding
import dev.logickoder.expensemanager.app.utils.collectAsState
import dev.logickoder.expensemanager.app.widgets.CheckboxItem
import dev.logickoder.expensemanager.app.widgets.DatePicker
import dev.logickoder.expensemanager.app.widgets.DropdownField
import dev.logickoder.expensemanager.ui.screens.shared.input.DefaultInputColor
import dev.logickoder.expensemanager.ui.screens.shared.input.Input
import dev.logickoder.expensemanager.ui.screens.shared.input.InputField
import dev.logickoder.expensemanager.ui.screens.shared.input.InputState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterForm(
    model: FilterFormModel,
    modifier: Modifier = Modifier,
    hideForm: () -> Unit,
    sectionSpacing: Dp = secondaryPadding()
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(
            sectionSpacing,
            Alignment.CenterVertically
        ),
        content = {
            DatePicker(
                title = stringResource(id = R.string.from),
                state = InputState(model.from.collectAsState().value),
                onDateChange = model.from::emit,
            )
            DatePicker(
                title = stringResource(id = R.string.to),
                state = InputState(model.to.collectAsState().value),
                onDateChange = model.to::emit,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(secondaryPadding()),
                content = {
                    val textIcon = Alignment.Start to Icons.Outlined.AttachMoney
                    Input(
                        modifier = Modifier.weight(0.45f),
                        title = stringResource(id = R.string.min),
                        state = InputState(
                            value = model.min.collectAsState().value,
                            onValueChanged = model.min::emit,
                            icon = textIcon,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                            ),
                        )
                    )
                    Input(
                        modifier = Modifier.weight(0.45f),
                        title = stringResource(id = R.string.max),
                        state = InputState(
                            value = model.max.collectAsState().value,
                            onValueChanged = model.max::emit,
                            icon = textIcon,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                            ),
                        )
                    )
                }
            )
            Input(
                title = stringResource(id = R.string.merchant),
                state = InputState(
                    value = model.merchant.collectAsState().value,
                    readOnly = true,
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
                                    } else Icons.Outlined.ArrowDropDown,
                                ),
                                interactionSource = interactionSource,
                            )
                        }
                    )
                }
            )
            Input(
                title = stringResource(id = R.string.status),
                state = InputState(model.status.collectAsState().value),
                content = { interactionSource, state ->
                    val statuses = stringArrayResource(id = R.array.statuses).toList()
                    statuses.chunked(2).forEach { row ->
                        Column(
                            modifier = Modifier.clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = {
                                    interactionSource.tryEmit(HoverInteraction.Enter())
                                }
                            ),
                            content = {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(secondaryPadding() / 2),
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
                        )
                    }
                }
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(secondaryPadding()),
                content = {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        content = {
                            Text(stringResource(id = R.string.filter))
                        },
                        onClick = {
                            model.save()
                            hideForm()
                        },
                    )
                    TextButton(
                        content = {
                            Text(stringResource(id = R.string.clear))
                        },
                        onClick = {
                            model.clear()
                            model.save()
                            hideForm()
                        },
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
