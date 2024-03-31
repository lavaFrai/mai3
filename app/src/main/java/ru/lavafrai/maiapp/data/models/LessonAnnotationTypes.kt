package ru.lavafrai.maiapp.data.models

import kotlinx.serialization.Serializable


@Serializable
data class LessonAnnotationType (
    val name: String,
    val priority: Long,
    val hasUserData: Boolean = false
)