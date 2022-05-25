package dev.logickoder.employee_expenses.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.theme.Theme


@Composable
fun HomeHeader(
    reimbursed: Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        content = {
            Text(
                text = stringResource(id = R.string.to_be_reimbursed, reimbursed.toString())
            )
            TextButton(
                onClick = {},
                content = {
                    Text(
                        text = stringResource(id = R.string.filters)
                    )
                    Icon(
                        imageVector = Icons.Outlined.FilterList,
                        contentDescription = stringResource(id = R.string.filters),
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = Theme.colors.primary,
                )
            )
        }
    )
} 