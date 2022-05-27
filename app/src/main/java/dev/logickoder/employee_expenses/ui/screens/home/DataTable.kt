package dev.logickoder.employee_expenses.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class DataTableState {
    var firstVisibleItemIndex by mutableStateOf(0)
    var firstVisibleItemScrollOffset by mutableStateOf(0)
}

@Composable
fun rememberDataTableState() = remember {
    DataTableState()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DataTable(
    modifier: Modifier = Modifier,
    state: DataTableState = rememberDataTableState(),
) {
    val data = listOf(
        "Date", "Merchant", "Total", "Status", "Comment"
    )

    @Composable
    fun Text(text: String) {
        Text(
            text = text,
            modifier = Modifier.width(100.dp)
        )
    }

    LazyColumn(
        modifier = modifier,
        content = {
            stickyHeader {
                DataRow(
                    tableState = state,
                    content = {
                        items(data) { string ->
                            Text(string)
                        }
                    }
                )
            }
            for (i in 1..100) {
                item {
                    DataRow(
                        tableState = state,
                        content = {
                            stickyHeader {
                                Text(data.first())
                            }
                            items(data) { string ->
                                Text(string)
                            }
                        }
                    )
                }
            }
        },
    )
}

@Composable
fun DataRow(
    state: LazyListState = rememberLazyListState(),
    tableState: DataTableState,
    content: LazyListScope.() -> Unit,
) {
    if (state.isScrollInProgress) {
        tableState.firstVisibleItemIndex = state.firstVisibleItemIndex
        tableState.firstVisibleItemScrollOffset = state.firstVisibleItemScrollOffset
    }
    LazyRow(
        state = state,
        content = content,
    )
    LaunchedEffect(
        key1 = tableState.firstVisibleItemIndex,
        key2 = tableState.firstVisibleItemScrollOffset,
        block = {
            state.scrollToItem(
                index = tableState.firstVisibleItemIndex,
                scrollOffset = tableState.firstVisibleItemScrollOffset,
            )
        }
    )
}
