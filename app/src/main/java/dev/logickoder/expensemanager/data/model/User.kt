package dev.logickoder.expensemanager.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val jobDescription: String,
    val department: String,
    val location: String,
    val avatar: String?,
)
