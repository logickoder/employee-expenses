package dev.logickoder.expense_manager.utils

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
private val currencyFormatter = DecimalFormat("#,###.00")

fun LocalDate.toText(): String = format(dateFormatter)

val Float.formatted: String
    get() = currencyFormatter.format(this)

val String.float: Float?
    get() = replace(",", "").toFloatOrNull()

fun Float.currency(currency: String = "$") = "$currency$formatted"