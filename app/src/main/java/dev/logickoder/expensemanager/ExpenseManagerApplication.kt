package dev.logickoder.expensemanager

import android.app.Application
import androidx.room.Room
import dev.logickoder.expensemanager.app.data.repository.DataRepository
import dev.logickoder.expensemanager.app.data.repository.UserRepository
import dev.logickoder.expensemanager.app.data.source.local.AppDatabase
import dev.logickoder.expensemanager.app.di.DependencyInjector
import dev.logickoder.expensemanager.app.di.DependencyInjectorImpl
import dev.logickoder.expensemanager.app.di.Provider


class ExpenseManagerApplication : Application(), Provider {
    override val provider: DependencyInjector = DependencyInjectorImpl()

    override fun onCreate() {
        super.onCreate()
        provideDependencies()
    }

    private fun provideDependencies() {
        provider[AppDatabase::class.java] = Room.databaseBuilder(
            this, AppDatabase::class.java, getString(R.string.app_name)
        ).build()
        provider[DataRepository::class.java] = DataRepository(provider[AppDatabase::class.java]!!)
        provider[UserRepository::class.java] = UserRepository(this)
    }
}