package dev.logickoder.expensemanager.app.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.logickoder.expensemanager.app.data.model.DataRow
import dev.logickoder.expensemanager.app.data.source.local.TypeConverters as Converters

@Database(entities = [DataRow::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): DataDao

    fun columnNames(table: String): Array<String> {
        return openHelper.writableDatabase.query("SELECT * FROM $table").columnNames
    }
}