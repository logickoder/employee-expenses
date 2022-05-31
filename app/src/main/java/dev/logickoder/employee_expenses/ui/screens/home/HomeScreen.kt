package dev.logickoder.employee_expenses.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.screens.shared.AppBar
import dev.logickoder.employee_expenses.ui.screens.shared.AppBarButton
import dev.logickoder.employee_expenses.ui.screens.shared.AppBarIconButton
import dev.logickoder.employee_expenses.ui.theme.Theme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
) {
    val horizontalPadding = dimensionResource(id = R.dimen.padding).let { padding ->
        padding - padding / 4
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                AppBar(
                    title = stringResource(id = R.string.app_name),
                    actions = {
                        AppBarButton(
                            text = stringResource(id = R.string.info),
                            onClick = {

                            }
                        )
                        AppBarIconButton(
                            icon = Icons.Outlined.Person,
                            onClick = {
                                state.navigateToProfileScreen()
                            }
                        )
                        AppBarIconButton(
                            icon = Icons.Outlined.Logout,
                            onClick = {
                                state.logout()
                            }
                        )
                    }
                )
                HomeHeader(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontalPadding),
                    reimbursed = state.reimbursed,
                    filterFormState = state.filterState,
                )
            }
        },
        backgroundColor = Theme.colors.surface,
        content = {
            Surface(
                modifier = modifier.padding(it),
                elevation = 4.dp,
                content = {
                    DataTable(
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null
                    )
                }
            )
        }
    )
}
