package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleLesson(
    val name: String,
    val timeRange: String,
    val type: ScheduleLessonType,
    val teacher: String,
    val location: String,
)
