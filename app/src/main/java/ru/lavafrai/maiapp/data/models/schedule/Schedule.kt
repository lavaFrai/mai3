package ru.lavafrai.maiapp.data.models.schedule

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable
import ru.lavafrai.maiapp.data.models.SerializableDate
import ru.lavafrai.maiapp.data.models.group.Group


@Serializable
@Immutable
@Stable
data class Schedule (
    val group: Group,
    val subSchedules: List<OneWeekSchedule>,
) {
    fun getWeeks(): List<ScheduleWeekId> {
        return subSchedules.map { it.weekId }
    }

    fun getWeek(number: Int): OneWeekSchedule? {
        return subSchedules.find { it.weekId.number == number }
    }

    fun getCurrentSubScheduleOrNull(): OneWeekSchedule? {
        return subSchedules.find { SerializableDate.now() in it.weekId.range }
    }

    fun getScheduleOfDay(day: SerializableDate): OneDaySchedule {
        val week = subSchedules.find { day in it.weekId.range }
        week ?: return getEmptyOneDaySchedule(day)
        val daySchedule = week.days.find { day == it.date }
        return daySchedule ?: getEmptyOneDaySchedule(day)
    }
}


fun getEmptySchedule(): Schedule {
    return Schedule(Group(""), listOf())
}
