package ru.lavafrai.maiapp.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AccountCredentials(
    val login: String,
    val password: String,
)