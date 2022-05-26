package dev.logickoder.employee_expenses.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.screens.shared.Input
import dev.logickoder.employee_expenses.ui.theme.secondaryPadding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeFilterFormState {
    var from by mutableStateOf<LocalDate?>(null)
    var to by mutableStateOf<LocalDate?>(null)
    var min by mutableStateOf<Double?>(null)
    var max by mutableStateOf<Double?>(null)
}

@Composable
fun rememberHomeFilterFormState() = remember {
    HomeFilterFormState()
}

@Composable
fun HomeFilterForm(
    state: HomeFilterFormState,
    modifier: Modifier = Modifier,
    sectionSpacing: Dp = secondaryPadding()
) = with(state) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(sectionSpacing, Alignment.CenterVertically),
        content = {
            val calendarIcon: Pair<Alignment.Horizontal, @Composable () -> Unit> =
                Alignment.End to {
                    Icon(
                        imageVector = Icons.Outlined.CalendarMonth,
                        contentDescription = null
                    )
                }
            Input(
                title = stringResource(id = R.string.from),
                value = from?.format(DateTimeFormatter.ISO_OFFSET_DATE) ?: "",
                onValueChanged = {},
                icon = calendarIcon
            )
            Input(
                title = stringResource(id = R.string.to),
                value = to?.format(DateTimeFormatter.ISO_OFFSET_DATE) ?: "",
                onValueChanged = {},
                icon = calendarIcon,
            )
            ConstraintLayout(
                modifier = Modifier.fillMaxWidth(),
                content = {
                    val (left, middle, right) = createRefs()
                    val textIcon: Pair<Alignment.Horizontal, @Composable () -> Unit> =
                        Alignment.End to {
                            Text("$")
                        }
                    Text(
                        text = "-",
                        modifier = Modifier.constrainAs(middle) {
                            val padding = sectionSpacing / 2
                            linkTo(parent.start, parent.end, padding, padding)
                        }
                    )
                    Input(
                        modifier = Modifier.constrainAs(left) {
                            linkTo(parent.start, middle.start)
                            width = Dimension.fillToConstraints
                        },
                        title = stringResource(id = R.string.min),
                        value = if (min == null) "" else min.toString(),
                        onValueChanged = {
                            min = it.toDoubleOrNull()
                        }
                    )
                    Input(
                        modifier = Modifier.constrainAs(right) {
                            linkTo(middle.end, parent.end)
                            width = Dimension.fillToConstraints
                        },
                        title = stringResource(id = R.string.max),
                        value = if (max == null) "" else max.toString(),
                        onValueChanged = {
                            max = it.toDoubleOrNull()
                        }
                    )
                }
            )
//            Input(title = , value = , onValueChanged = )
        }
    )
}
