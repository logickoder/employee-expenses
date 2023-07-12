package dev.logickoder.expensemanager.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.logickoder.expensemanager.R
import dev.logickoder.expensemanager.ui.screens.shared.AppBar
import dev.logickoder.expensemanager.ui.screens.shared.AppBarIconButton
import dev.logickoder.expensemanager.ui.screens.shared.DataTable
import dev.logickoder.expensemanager.ui.screens.shared.expense_form.ExpenseForm
import dev.logickoder.expensemanager.ui.screens.shared.expense_form.ExpenseFormState
import dev.logickoder.expensemanager.ui.theme.primaryPadding
import dev.logickoder.expensemanager.ui.theme.secondaryPadding
import dev.logickoder.expensemanager.utils.collectAsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    navigateToProfileScreen: () -> Unit,
    logout: () -> Unit,
) = with(state) {

    var formHidden by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val expenseState by expense.collectAsState()
    val dismiss: () -> Unit = {
        coroutineScope.launch {
            sheetState.hide()
        }
    }

    BackHandler(
        enabled = sheetState.isVisible,
        onBack = dismiss,
    )

    Scaffold(
        modifier = modifier,
        topBar = {
            AppBar(
                title = stringResource(id = R.string.app_name),
                actions = {
                    AppBarIconButton(
                        icon = Icons.Outlined.Person,
                        onClick = navigateToProfileScreen,
                    )
                    AppBarIconButton(
                        icon = Icons.Outlined.Logout,
                        onClick = logout,
                    )
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .padding(paddingValues),
                content = {
                    HomeHeader(
                        modifier = Modifier.padding(secondaryPadding()),
                        reimbursed = reimbursed.collectAsState(0f).value,
                        filterFormState = state.filterFormState,
                        filterFormHidden = formHidden,
                        changeFilterFormHidden = {
                            formHidden = it
                        }
                    )
                    Surface(
                        shadowElevation = 4.dp,
                        content = {
                            DataTable(
                                headers = repository.headers.collectAsState().value,
                                items = repository.data.collectAsState().value,
                                onHeaderClick = { header ->
                                    coroutineScope.launch {
                                        repository.sort(header)
                                    }
                                },
                                onRowClick = { row ->
                                    expense.emit(ExpenseFormState(row))
                                    coroutineScope.launch {
                                        sheetState.expand()
                                    }
                                }
                            )
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            if (formHidden) FloatingActionButton(
                onClick = {
                    if (expenseState.data != null) expense.emit(ExpenseFormState())
                    coroutineScope.launch {
                        sheetState.expand()
                    }
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

    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = dismiss,
            content = {
                Scaffold(
                    topBar = {
                        AppBar(
                            title = stringResource(
                                id = if (expenseState.isEdit) {
                                    R.string.edit_expense
                                } else R.string.add_expense
                            ),
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.surface,
                    content = { padding ->
                        Column(
                            modifier = Modifier
                                .padding(padding)
                                .padding(horizontal = primaryPadding())
                                .verticalScroll(rememberScrollState()),
                            content = {
                                Spacer(modifier = Modifier.height(primaryPadding()))
                                ExpenseForm(
                                    state = expenseState,
                                    onSaveClicked = {
                                        coroutineScope.launch {
                                            if (save()) dismiss()
                                        }
                                    },
                                    onCancelClicked = dismiss,
                                    onDeleteClicked = {
                                        expenseState.clear()
                                        dismiss()
                                    },
                                )
                                Spacer(modifier = Modifier.height(primaryPadding()))
                            }
                        )
                    }
                )
            }
        )
    }
}
