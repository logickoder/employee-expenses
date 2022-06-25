package dev.logickoder.expense_manager.data.repository

import androidx.sqlite.db.SimpleSQLiteQuery
import dev.logickoder.expense_manager.data.model.DataHeader
import dev.logickoder.expense_manager.data.model.DataRow
import dev.logickoder.expense_manager.data.source.local.AppDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class DataRepository(database: AppDatabase) {
    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    private val dao = database.dao()
    private var lastSortHeader: DataHeader? = null

    val headers = MutableStateFlow(
        database.columnNames(DataRow.TableName).map {
            DataHeader(it, DataHeader.SortType.None)
        }.run {
            subList(1, lastIndex)
        }
    )

    val data = MutableStateFlow(emptyList<DataRow>()).also {
        sort(lastSortHeader)
    }

    fun add(data: DataRow) {
        scope.launch {
            dao.insert(data)
            sort(lastSortHeader)
        }
    }

    fun sort(header: DataHeader?) {
        // validate that this header is in the table
        if (header != null) {
            headers.value.firstOrNull { it.value == header.value } ?: return
        }
        // update the headers
        val updatedList = headers.value.map {
            it.copy(
                sortType = if (it == header) it.sortType.switch() else DataHeader.SortType.None
            )
        }

        scope.launch {
            val query = "SELECT * FROM data ORDER BY ${header?.value} %s"
            val sortedData = when (header?.sortType?.switch()) {
                DataHeader.SortType.Up -> dao.runtimeQuery(
                    SimpleSQLiteQuery(query.format("ASC"))
                )
                DataHeader.SortType.Down -> dao.runtimeQuery(
                    SimpleSQLiteQuery(query.format("DESC"))
                )
                DataHeader.SortType.None, null -> dao.getAll()
            }
            lastSortHeader = header
            withContext(Dispatchers.Main) {
                headers.emit(updatedList)
                data.emit(sortedData)
            }
        }
    }
}