package dev.logickoder.expense_manager.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

fun LocalDate?.toText() = this?.format(dateFormatter) ?: ""
fun String.toDate(): LocalDate = LocalDate.from(dateFormatter.parse(this))