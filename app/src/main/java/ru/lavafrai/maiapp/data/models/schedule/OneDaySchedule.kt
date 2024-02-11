package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable
import ru.lavafrai.maiapp.data.models.SerializableDate
import java.time.DayOfWeek

@Serializable
data class OneDaySchedule (
    val lessons: List<ScheduleLesson>,
    val dayOfWeek: DayOfWeek,
    val date: SerializableDate,
    val lessonsCount: Int = lessons.size
)
