package dev.logickoder.expensemanager.app.di

/**
 * Interface representing a dependency provider.
 * This interface allows setting and retrieving instances of dependencies.
 */
interface DependencyProvider {

    /**
     * Sets an instance of a dependency.
     * @param klass The class of the dependency.
     * @param instance The instance of the dependency to be set.
     */
    operator fun <T> set(klass: Class<T>, instance: T)

    /**
     * Retrieves an instance of a dependency.
     * @param klass The class of the dependency.
     * @return The instance of the dependency if found, or null if not available.
     */
    operator fun <T> get(klass: Class<T>): T?
}
