package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable


@Serializable
data class OneWeekSchedule (
    val weekId: ScheduleWeekId,
    val days: List<OneDaySchedule>,
)

fun getEmptyOneWeekSchedule(): OneWeekSchedule {
    return OneWeekSchedule(
        ScheduleWeekId(0, ""),
        days = listOf(),
    )
}
