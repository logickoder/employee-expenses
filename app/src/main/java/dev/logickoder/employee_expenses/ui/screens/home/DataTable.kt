package dev.logickoder.employee_expenses.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DataTable(
    modifier: Modifier = Modifier,
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

    val rowState = rememberLazyListState()
    LazyColumn(
        modifier = modifier,
        content = {
            stickyHeader {
                LazyRow(
                    state = rowState,
                    content = {
                        items(data) { string ->
                            Text(string)
                        }
                    }
                )
            }
            for (i in 1..100) {
                item {
                    LazyRow(
                        state = rowState,
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
