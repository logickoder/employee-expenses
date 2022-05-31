package dev.logickoder.employee_expenses.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.logickoder.employee_expenses.ui.theme.secondaryPadding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DataTable(
    modifier: Modifier = Modifier,
) {
    val title = remember {
        listOf(
            "Date", "Merchant", "Total", "Status", "Comment"
        )
    }
    val data = remember {
        (1..100).flatMap {
            title
        }
    }

    @Composable
    fun Text(text: String) {
        Text(
            text = text,
            modifier = Modifier.width(100.dp)
        )
    }

    LazyColumn(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        content = {
            stickyHeader {
                DataRow(
                    items = title,
                    itemCreator = {
                        Text(it)
                    },
                )
            }
            data.chunked(title.size).forEach { row ->
                item {
                    DataRow(
                        items = row,
                        itemCreator = {
                            DataItem(modifier =, title =)
                        }
                    )
                }
            }
        },
    )
}

@Composable
fun <T> DataRow(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemCreator: @Composable (T) -> Unit,
) {
    Row(
        modifier = modifier,
        content = {
            items.forEach { item ->
                itemCreator(item)
            }
        },
    )
}

@Composable
fun DataItem(
    modifier: Modifier,
    title: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(secondaryPadding() / 2),
        content = {
            Divider()
            Text(
                text = title,
            )
            Divider()
        }
    )
}
