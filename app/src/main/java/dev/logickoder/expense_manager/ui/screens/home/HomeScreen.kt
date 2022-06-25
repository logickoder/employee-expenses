package dev.logickoder.expense_manager.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.logickoder.expense_manager.R
import dev.logickoder.expense_manager.ui.screens.shared.AppBar
import dev.logickoder.expense_manager.ui.screens.shared.AppBarIconButton
import dev.logickoder.expense_manager.ui.screens.shared.DataTable
import dev.logickoder.expense_manager.ui.screens.shared.expense_form.ExpenseForm
import dev.logickoder.expense_manager.ui.screens.shared.expense_form.ExpenseFormState
import dev.logickoder.expense_manager.ui.theme.Theme
import dev.logickoder.expense_manager.ui.theme.primaryPadding
import dev.logickoder.expense_manager.ui.theme.secondaryPadding
import dev.logickoder.expense_manager.utils.collectAsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    navigateToProfileScreen: () -> Unit,
    logout: () -> Unit,
) = with(state) {

    val coroutineScope = rememberCoroutineScope()
    val modalState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val expenseState by expense.collectAsState()
    val dismiss: () -> Unit = {
        coroutineScope.launch {
            modalState.animateTo(ModalBottomSheetValue.Hidden)
        }
    }

    BackHandler(
        enabled = modalState.isVisible,
        onBack = dismiss,
    )

    ModalBottomSheetLayout(
        sheetState = modalState,
        sheetContent = {
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
                backgroundColor = Theme.colors.surface,
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
                                    if (save()) dismiss()
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
        },
        content = {
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
                backgroundColor = Theme.colors.surface,
                content = { paddingValues ->
                    Column(
                        modifier = modifier
                            .padding(paddingValues),
                        content = {
                            HomeHeader(
                                modifier = Modifier.padding(secondaryPadding()),
                                reimbursed = reimbursed.collectAsState().value,
                                filterFormState = state.filterFormState,
                            )
                            Surface(
                                elevation = 4.dp,
                                content = {
                                    DataTable(
                                        headers = repository.headers.collectAsState().value,
                                        items = repository.data.collectAsState().value,
                                        onHeaderClick = { header ->
                                            repository.sort(header)
                                        },
                                        onRowClick = { row ->
                                            expense.emit(ExpenseFormState(row))
                                            coroutineScope.launch {
                                                modalState.animateTo(ModalBottomSheetValue.Expanded)
                                            }
                                        }
                                    )
                                }
                            )
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            if (expenseState.data != null) expense.emit(ExpenseFormState())
                            coroutineScope.launch {
                                modalState.animateTo(ModalBottomSheetValue.Expanded)
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
        }
    )
}
