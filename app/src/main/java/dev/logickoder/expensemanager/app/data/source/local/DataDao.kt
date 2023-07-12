package dev.logickoder.expensemanager.app.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import dev.logickoder.expensemanager.app.data.model.DataRow

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg data: DataRow)

    @Query("SELECT * FROM data")
    suspend fun getAll(): List<DataRow>

    @RawQuery
    suspend fun runtimeQuery(query: SupportSQLiteQuery): List<DataRow>
}