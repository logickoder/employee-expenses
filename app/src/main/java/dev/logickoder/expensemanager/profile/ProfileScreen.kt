package dev.logickoder.expensemanager.profile

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
import androidx.compose.ui.platform.LocalContext
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
    model: ProfileScreenModel,
    goBack: () -> Unit,
    logout: () -> Unit,
    modifier: Modifier = Modifier,
    sectionSpacing: Dp = secondaryPadding(),
) {

    val context = LocalContext.current
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
                            model.avatar.emit(it)
                        },
                        content = {
                            Avatar(
                                model = model.avatar.collectAsState().value,
                                placeholder = rememberVectorPainter(Icons.Outlined.Person),
                                onClick = it
                            )
                        }
                    )
                    Input(
                        title = stringResource(id = R.string.name),
                        state = InputState(
                            value = model.name.collectAsState().value,
                            error = model.nameError.collectAsState().value,
                            onValueChanged = model.name::emit,
                            required = true,
                        )
                    )
                    Input(
                        title = stringResource(id = R.string.job_description),
                        state = InputState(
                            value = model.jobDescription.collectAsState().value,
                            error = model.jobDescriptionError.collectAsState().value,
                            onValueChanged = model.jobDescription::emit,
                            required = true,
                        )
                    )
                    Input(
                        title = stringResource(id = R.string.department),
                        state = InputState(
                            value = model.department.collectAsState().value,
                            error = model.departmentError.collectAsState().value,
                            onValueChanged = model.department::emit,
                            required = true,
                        )
                    )
                    Input(
                        title = stringResource(id = R.string.location),
                        state = InputState(
                            value = model.location.collectAsState().value,
                            error = model.locationError.collectAsState().value,
                            onValueChanged = model.location::emit,
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
                            if (model.save() != null) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = context.getString(R.string.profile_updated)
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
