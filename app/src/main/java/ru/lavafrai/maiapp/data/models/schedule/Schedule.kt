package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable
import ru.lavafrai.maiapp.data.models.group.GroupId


@Serializable
data class Schedule (
    val groupId: GroupId,
    val subSchedules: List<OneWeekSchedule>,
) {
    fun getWeeks(): List<ScheduleWeekId> {
        return subSchedules.map { it.weekId }
    }

    fun getWeek(number: Int): OneWeekSchedule? {
        return subSchedules.find { it.weekId.number == number }
    }
}


fun getEmptySchedule(): Schedule {
    return Schedule(GroupId(""), listOf())
}
