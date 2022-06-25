package dev.logickoder.expense_manager.ui.screens.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.logickoder.expense_manager.R
import dev.logickoder.expense_manager.ui.screens.shared.filter_form.FilterForm
import dev.logickoder.expense_manager.ui.screens.shared.filter_form.FilterFormState


@Composable
fun HomeHeader(
    reimbursed: Float,
    filterFormState: FilterFormState,
    modifier: Modifier = Modifier
) {
    var formHidden by remember { mutableStateOf(true) }

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
                                    formHidden = !formHidden
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
            Box(
                modifier = Modifier.animateContentSize(),
                content = {
                    if (!formHidden) FilterForm(state = filterFormState)
                }
            )
        }
    )
} 
