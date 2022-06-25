package dev.logickoder.expense_manager.ui.screens.shared.filter_form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import dev.logickoder.expense_manager.R
import dev.logickoder.expense_manager.ui.screens.shared.CheckboxItem
import dev.logickoder.expense_manager.ui.screens.shared.DatePicker
import dev.logickoder.expense_manager.ui.screens.shared.DropdownField
import dev.logickoder.expense_manager.ui.screens.shared.input.Input
import dev.logickoder.expense_manager.ui.screens.shared.input.InputField
import dev.logickoder.expense_manager.ui.screens.shared.input.InputState
import dev.logickoder.expense_manager.ui.screens.shared.input.InputWithField
import dev.logickoder.expense_manager.ui.theme.secondaryPadding

@Composable
fun FilterForm(
    state: FilterFormState,
    modifier: Modifier = Modifier,
    sectionSpacing: Dp = secondaryPadding()
) {
    val from by state.from.collectAsState(initial = "")
    val to by state.to.collectAsState(initial = "")
    val min by state.min.collectAsState(initial = "")
    val max by state.max.collectAsState(initial = "")
    val merchant by state.merchant.collectAsState(initial = "")
    val merchants = stringArrayResource(id = R.array.merchants).toList()
    val status by state.status.collectAsState(initial = null)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            sectionSpacing,
            Alignment.CenterVertically
        ),
        content = {
            DatePicker(
                title = stringResource(id = R.string.from),
                state = InputState(from),
                onDateChange = state::updateFromDate,
            )
            DatePicker(
                title = stringResource(id = R.string.to),
                state = InputState(to),
                onDateChange = state::updateToDate,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(secondaryPadding()),
                content = {
                    val textIcon = Alignment.Start to Icons.Outlined.AttachMoney
                    InputWithField(
                        modifier = Modifier.weight(0.45f),
                        title = stringResource(id = R.string.min),
                        state = InputState(
                            value = min,
                            onValueChanged = state::updateMinAmount,
                            icon = textIcon,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                            ),
                        )
                    )
                    InputWithField(
                        modifier = Modifier.weight(0.45f),
                        title = stringResource(id = R.string.max),
                        state = InputState(
                            value = max,
                            onValueChanged = state::updateMaxAmount,
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
                content = { interactionSource ->
                    DropdownField(
                        suggested = merchant,
                        suggestions = merchants,
                        onSuggestionSelected = state::updateMerchant,
                        dropdownField = { suggested, expanded ->
                            InputField(
                                state = InputState(
                                    value = suggested,
                                    readOnly = true,
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
                content = { interactionSource ->
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
                                                checked = status == item,
                                                onCheckedChange = { isChecked ->
                                                    state.updateStatus(item, isChecked)
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
        }
    )
}
