package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleLesson(
    val name: String,
    val timeRange: String,
    val type: ScheduleLessonType,
    val teacher: String,
    val location: String,

) {
    fun getPairNumber(): Int {
        return when (timeRange) {
            "09:00 – 10:30" -> 1
            "10:45 – 12:15" -> 2
            "13:00 – 14:30" -> 3
            "14:45 – 16:15" -> 4
            "16:30 – 18:00" -> 5
            "18:15 – 19:45" -> 6
            "20:00 – 21:30" -> 7
            else -> 0
        }
    }

    fun getStartTime(): String {
        return timeRange.split(" – ")[0]
    }

    fun getEndTime(): String {
        return timeRange.split(" – ")[1]
    }
}
