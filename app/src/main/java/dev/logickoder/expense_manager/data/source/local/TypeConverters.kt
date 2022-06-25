package dev.logickoder.expense_manager.data.source.local

import android.net.Uri
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.logickoder.expense_manager.utils.toDate
import dev.logickoder.expense_manager.utils.toText
import java.time.LocalDate


class TypeConverters {
    private val gson = Gson()

    private fun <T> T?.toJson(): String? {
        return if (this == null) {
            null
        } else gson.toJson(this)
    }

    private fun <T> String?.fromJson(): T? {
        return if (this == null) {
            null
        } else gson.fromJson(this, object : TypeToken<T>() {}.type)
    }

    @TypeConverter
    fun String?.toLocalDate() = this?.toDate()

    @TypeConverter
    fun LocalDate?.fromLocalDate() = toText()

    @TypeConverter
    fun String?.toUri() = this?.let {
        Uri.parse(it)
    }

    @TypeConverter
    fun Uri?.string() = this?.let {
        it.toString()
    }
}