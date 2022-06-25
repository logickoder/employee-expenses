package dev.logickoder.expense_manager.ui.domain


interface FormState<T> {
    fun hasError(): Boolean
    fun clearErrors()
    fun save(): T?
    fun clear()
}