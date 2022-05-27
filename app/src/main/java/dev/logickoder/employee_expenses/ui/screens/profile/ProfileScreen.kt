package dev.logickoder.employee_expenses.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import dev.logickoder.employee_expenses.R
import dev.logickoder.employee_expenses.ui.screens.shared.AppBar
import dev.logickoder.employee_expenses.ui.screens.shared.AppBarButton
import dev.logickoder.employee_expenses.ui.screens.shared.Input
import dev.logickoder.employee_expenses.ui.theme.secondaryPadding

@Composable
fun ProfileScreen(
    profileState: ProfileState,
    modifier: Modifier = Modifier,
    sectionSpacing: Dp = secondaryPadding(),
) = with(profileState) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                title = stringResource(id = R.string.app_name),
                navigateBack = navigateToHomeScreen,
                actions = {
                    AppBarButton(
                        text = stringResource(id = R.string.logout),
                        onClick = {}
                    )
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding),
                verticalArrangement = Arrangement.spacedBy(
                    sectionSpacing,
                    Alignment.CenterVertically
                ),
                content = {
                    Input(
                        title = stringResource(id = R.string.name),
                        value = name,
                        onValueChanged = {
                            name = it
                        },
                    )
                    Input(
                        title = stringResource(id = R.string.job_description),
                        value = jobDescription,
                        onValueChanged = {
                            jobDescription = it
                        },
                    )
                    Input(
                        title = stringResource(id = R.string.department),
                        value = department,
                        onValueChanged = {
                            department = it
                        },
                    )
                    Input(
                        title = stringResource(id = R.string.location),
                        value = location,
                        onValueChanged = {
                            location = it
                        },
                    )
                }
            )
        }
    )
}
