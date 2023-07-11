package dev.logickoder.expensemanager.di


interface DependencyInjector {
    operator fun <T> set(klass: Class<T>, instance: T)
    operator fun <T> get(klass: Class<T>): T?
}