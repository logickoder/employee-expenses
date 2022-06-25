package dev.logickoder.expense_manager.data.source.local

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import dev.logickoder.expense_manager.data.model.DataRow

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg data: DataRow)

    @Query("SELECT * FROM data")
    suspend fun getAll(): List<DataRow>

    @RawQuery
    suspend fun runtimeQuery(query: SupportSQLiteQuery): List<DataRow>
}