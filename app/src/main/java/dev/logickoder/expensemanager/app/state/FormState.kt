package dev.logickoder.expensemanager.app.state


abstract class FormState<T> {
    protected open val errors: List<MutableObservableState<String?, String?, String?>> = emptyList()

    fun hasError() = errors.any { it.value != null }

    fun clearErrors() = errors.forEach { it.emit(null) }

    abstract suspend fun save(): T?

    open fun clear() {}
}