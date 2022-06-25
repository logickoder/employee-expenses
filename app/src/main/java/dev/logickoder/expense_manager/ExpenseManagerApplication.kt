package dev.logickoder.expense_manager

import android.app.Application
import androidx.room.Room
import dev.logickoder.expense_manager.data.repository.DataRepository
import dev.logickoder.expense_manager.data.source.local.AppDatabase
import dev.logickoder.expense_manager.di.DependencyInjector
import dev.logickoder.expense_manager.di.DependencyInjectorImpl
import dev.logickoder.expense_manager.di.Provider


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
    }
}