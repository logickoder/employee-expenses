package dev.logickoder.expensemanager.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.logickoder.expensemanager.R
import dev.logickoder.expensemanager.app.utils.collectAsState
import dev.logickoder.expensemanager.app.utils.currency
import dev.logickoder.expensemanager.app.widgets.filterform.FilterForm
import dev.logickoder.expensemanager.app.widgets.filterform.FilterFormModel


@Composable
fun HomeHeader(
    filterFormHidden: Boolean,
    reimbursed: Float,
    filterFormModel: FilterFormModel,
    changeFilterFormHidden: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        content = {
            ProvideTextStyle(
                value = MaterialTheme.typography.labelLarge,
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        content = {
                            Text(
                                text = stringResource(
                                    id = R.string.to_be_reimbursed,
                                    reimbursed.currency(),
                                )
                            )
                            Box(
                                content = {
                                    TextButton(
                                        onClick = {
                                            changeFilterFormHidden(!filterFormHidden)
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
                                    val from by filterFormModel.from.collectAsState()
                                    val to by filterFormModel.to.collectAsState()
                                    val max by filterFormModel.max.collectAsState()
                                    val min by filterFormModel.min.collectAsState()
                                    val merchant by filterFormModel.merchant.collectAsState()
                                    val status by filterFormModel.status.collectAsState()
                                    val count = listOf(from, to, max, min, merchant, status)
                                        .count { it.isNotBlank() }

                                    if (count > 0) Text(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .background(
                                                MaterialTheme.colorScheme.error,
                                                CircleShape
                                            )
                                            .padding(2.dp),
                                        text = count.toString(),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onError,
                                    )
                                }
                            )
                        }
                    )
                }
            )
            Box(
                modifier = Modifier.animateContentSize(),
                content = {
                    if (!filterFormHidden) FilterForm(
                        model = filterFormModel,
                        hideForm = {
                            changeFilterFormHidden(true)
                        }
                    )
                }
            )
        }
    )
} 
