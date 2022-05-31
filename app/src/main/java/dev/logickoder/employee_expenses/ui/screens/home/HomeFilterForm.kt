package dev.logickoder.employee_expenses.ui.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.CalendarViewMonth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.screens.shared.InputWithField
import dev.logickoder.employee_expenses.ui.theme.secondaryPadding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeFilterFormState {
    private val dateFormatter = DateTimeFormatter.ofPattern("dd/mm/yyyy")

    internal fun LocalDate?.toText() = this?.format(dateFormatter) ?: ""
    internal fun String.toDate(): LocalDate = LocalDate.from(dateFormatter.parse(this))

    internal fun String.toAmount() = toDoubleOrNull()
    internal fun Double?.toText() = this?.toString() ?: ""


    var from by mutableStateOf<LocalDate?>(null)
    var to by mutableStateOf<LocalDate?>(null)
    var min by mutableStateOf<Double?>(null)
    var max by mutableStateOf<Double?>(null)
    var hidden by mutableStateOf(false)
}

@Composable
fun HomeFilterForm(
    state: HomeFilterFormState,
    modifier: Modifier = Modifier,
    sectionSpacing: Dp = secondaryPadding()
) = with(state) {
    Box(
        modifier = modifier.animateContentSize(),
        content = {
            if (!hidden) Column(
                verticalArrangement = Arrangement.spacedBy(
                    sectionSpacing,
                    Alignment.CenterVertically
                ),
                content = {
                    val calendarIcon = Alignment.End to Icons.Outlined.CalendarViewMonth
                    InputWithField(
                        title = stringResource(id = R.string.from),
                        value = from.toText(),
                        onValueChanged = {
                            from = it.toDate()
                        },
                        icon = calendarIcon
                    )
                    InputWithField(
                        title = stringResource(id = R.string.to),
                        value = to.toText(),
                        onValueChanged = {
                            to = it.toDate()
                        },
                        icon = calendarIcon,
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
                                value = min.toText(),
                                onValueChanged = {
                                    min = it.toAmount() ?: min
                                },
                                icon = textIcon,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                ),
                            )
                            InputWithField(
                                modifier = Modifier.weight(0.45f),
                                title = stringResource(id = R.string.max),
                                value = max.toText(),
                                onValueChanged = {
                                    max = it.toAmount() ?: max
                                },
                                icon = textIcon,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                ),
                            )
                        }
                    )
                }
            )
        }
    )
}
