package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable
import java.time.DayOfWeek

@Serializable
data class OneDaySchedule (
    val lessons: List<ScheduleLesson>,
    val dayOfWeek: DayOfWeek,
    val date: String,
    val lessonsCount: Int = lessons.size
)
