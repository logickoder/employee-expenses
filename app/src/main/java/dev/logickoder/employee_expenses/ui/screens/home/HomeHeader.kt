package dev.logickoder.employee_expenses.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.logickoder.employee_expenses.R


@Composable
fun HomeHeader(
    reimbursed: Float,
    modifier: Modifier = Modifier
) {
    val filterFormState = rememberHomeFilterFormState()
    Column(
        modifier = modifier,
        content = {
            ProvideTextStyle(
                value = MaterialTheme.typography.button,
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        content = {
                            Text(
                                text = stringResource(
                                    id = R.string.to_be_reimbursed,
                                    reimbursed.toString()
                                )
                            )
                            TextButton(
                                onClick = {
                                    filterFormState.hidden = filterFormState.hidden.not()
                                },
                                content = {
                                    Text(
                                        text = stringResource(id = R.string.filters)
                                    )
                                    Icon(
                                        imageVector = Icons.Outlined.FilterList,
                                        contentDescription = stringResource(id = R.string.filters),
                                    )
                                },
                            )
                        }
                    )
                }
            )
            HomeFilterForm(state = filterFormState)
        }
    )
} 
