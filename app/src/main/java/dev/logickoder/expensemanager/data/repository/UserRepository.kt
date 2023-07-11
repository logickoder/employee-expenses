package dev.logickoder.expensemanager.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.logickoder.expensemanager.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val userString = "user"
val Context.userStore: DataStore<Preferences> by preferencesDataStore(name = userString)
private val USER = stringPreferencesKey(userString)

/**
 * Stores the user info on the device
 */
class UserRepository(val context: Context) {
    suspend fun save(data: User) {
        context.userStore.edit { preferences ->
            preferences[USER] = Json.encodeToString(data)
        }
    }

    fun get(): Flow<User?> {
        return context.userStore.data.map { preferences ->
            return@map when (val user = preferences[USER]) {
                null -> null
                else -> Json.decodeFromString<User>(user)
            }
        }
    }

    suspend fun clear() {
        context.userStore.edit { preferences -> preferences.clear() }
    }
}
