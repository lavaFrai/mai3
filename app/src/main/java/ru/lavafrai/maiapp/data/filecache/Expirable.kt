package ru.lavafrai.maiapp.data.filecache

import kotlinx.serialization.Serializable


@Serializable
data class Expirable<T>(
    val expires: Long,
    val data: T,
)
