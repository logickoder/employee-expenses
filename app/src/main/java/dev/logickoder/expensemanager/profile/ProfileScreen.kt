package dev.logickoder.expensemanager.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import dev.logickoder.expensemanager.R
import dev.logickoder.expensemanager.app.theme.primaryPadding
import dev.logickoder.expensemanager.app.theme.secondaryPadding
import dev.logickoder.expensemanager.app.utils.collectAsState
import dev.logickoder.expensemanager.app.widgets.AppBar
import dev.logickoder.expensemanager.app.widgets.AppBarIconButton
import dev.logickoder.expensemanager.app.widgets.Avatar
import dev.logickoder.expensemanager.app.widgets.ImageSelect
import dev.logickoder.expensemanager.ui.screens.shared.input.Input
import dev.logickoder.expensemanager.ui.screens.shared.input.InputState
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    state: ProfileState,
    goBack: () -> Unit,
    logout: () -> Unit,
    modifier: Modifier = Modifier,
    sectionSpacing: Dp = secondaryPadding(),
) = with(state) {

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            AppBar(
                title = stringResource(id = R.string.profile),
                navigateBack = goBack,
                actions = {
                    AppBarIconButton(
                        icon = Icons.Outlined.Logout,
                        onClick = logout
                    )
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
                    .padding(primaryPadding()),
                verticalArrangement = Arrangement.spacedBy(
                    sectionSpacing,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    ImageSelect(
                        onImageSelected = {
                            avatar.emit(it)
                        },
                        content = {
                            Avatar(
                                model = avatar.collectAsState().value,
                                placeholder = rememberVectorPainter(Icons.Outlined.Person),
                                onClick = it
                            )
                        }
                    )
                    Input(
                        title = stringResource(id = R.string.name),
                        state = InputState(
                            value = name.collectAsState().value,
                            error = nameError.collectAsState().value,
                            onValueChanged = name::emit,
                            required = true,
                        )
                    )
                    Input(
                        title = stringResource(id = R.string.job_description),
                        state = InputState(
                            value = jobDescription.collectAsState().value,
                            error = jobDescriptionError.collectAsState().value,
                            onValueChanged = jobDescription::emit,
                            required = true,
                        )
                    )
                    Input(
                        title = stringResource(id = R.string.department),
                        state = InputState(
                            value = department.collectAsState().value,
                            error = departmentError.collectAsState().value,
                            onValueChanged = department::emit,
                            required = true,
                        )
                    )
                    Input(
                        title = stringResource(id = R.string.location),
                        state = InputState(
                            value = location.collectAsState().value,
                            error = locationError.collectAsState().value,
                            onValueChanged = location::emit,
                            required = true,
                        )
                    )
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = secondaryPadding()),
                        shape = MaterialTheme.shapes.large,
                        content = {
                            Text(stringResource(id = R.string.save).uppercase())
                        },
                        onClick = {
                            coroutineScope.launch {
                                if (save() != null) {
                                    snackbarHostState.showSnackbar(
                                        "Profile updated successfully"
                                    )
                                }
                            }
                        },
                    )
                }
            )
        }
    )
}
