package dev.logickoder.employee_expenses.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import dev.logickoder.employee_expenses.ui.screens.shared.Input
import dev.logickoder.employee_expenses.ui.theme.secondaryPadding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate

class HomeFilterFormState(
    scope: CoroutineScope
) {
    private val _from: MutableStateFlow<LocalDate?> = MutableStateFlow(null)
    val from: Flow<LocalDate?>
        get() = _from

    private val _to: MutableStateFlow<LocalDate?> = MutableStateFlow(null)
    val to: Flow<LocalDate?>
        get() = _to

    private val _min: MutableStateFlow<Double?> = MutableStateFlow(null)
    val min: Flow<Double?>
        get() = _min

    private val _max: MutableStateFlow<Double?> = MutableStateFlow(null)
    val max: Flow<Double?>
        get() = _max
}

@Composable
fun HomeFilterForm(
    modifier: Modifier = Modifier,
    sectionSpacing: Dp = secondaryPadding()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(sectionSpacing, Alignment.CenterVertically),
        content = {
            Input(title = , value = , onValueChanged = )
            Input(title = , value = , onValueChanged = )
            Row(
                content = {
                    Input(title = , value = , onValueChanged = )
                    Input(title = , value = , onValueChanged = )
                }
            )
            Input(title = , value = , onValueChanged = )
        }
    )
}
