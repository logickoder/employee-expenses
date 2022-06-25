package dev.logickoder.expense_manager.utils

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
private val currencyFormatter = DecimalFormat("#,###.00")

fun LocalDate.toText(): String = format(dateFormatter)
fun String.toDate(): LocalDate = LocalDate.from(dateFormatter.parse(this))

fun Float.currency(currency: String = "$") = "$currency${currencyFormatter.format(this)}"