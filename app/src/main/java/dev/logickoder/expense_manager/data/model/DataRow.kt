package dev.logickoder.expense_manager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.logickoder.expense_manager.utils.currency
import dev.logickoder.expense_manager.utils.toText
import java.time.LocalDate

@Entity(tableName = DataRow.TableName)
data class DataRow(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate,
    val merchant: String,
    val total: Float,
    val status: String,
    val comment: String,
    val receipt: String,
) {
    val items: List<String>
        get() {
            return listOf(
                date.toText(),
                merchant,
                total.currency(),
                status,
                comment,
            )
        }

    companion object {
        const val TableName = "data"
    }
}
