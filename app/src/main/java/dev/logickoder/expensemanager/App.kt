package dev.logickoder.expensemanager

import android.app.Application
import androidx.room.Room
import dev.logickoder.expensemanager.app.data.repository.DataRepository
import dev.logickoder.expensemanager.app.data.repository.UserRepository
import dev.logickoder.expensemanager.app.data.source.local.AppDatabase
import dev.logickoder.expensemanager.app.di.DependencyProvider


class App : Application(), DependencyProvider {

    private val cache = mutableMapOf<Class<*>, Any>()

    override fun onCreate() {
        super.onCreate()
        provideDependencies()
    }

    override fun <T> set(klass: Class<T>, instance: T) {
        cache[klass] = instance as Any
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(klass: Class<T>): T {
        return if (klass !in cache.keys) {
            throw IllegalArgumentException("Class not found in cache")
        } else cache[klass] as T
    }

    private fun provideDependencies() {
        set(
            AppDatabase::class.java, Room.databaseBuilder(
                this, AppDatabase::class.java, getString(R.string.app_name)
            ).build()
        )
        set(DataRepository::class.java, DataRepository(get(AppDatabase::class.java)))
        set(UserRepository::class.java, UserRepository(this))
    }
}