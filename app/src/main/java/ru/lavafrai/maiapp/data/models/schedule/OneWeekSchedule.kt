package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable
import ru.lavafrai.maiapp.data.models.DateRange


@Serializable
data class OneWeekSchedule (
    val weekId: ScheduleWeekId,
    val days: List<OneDaySchedule>,
)

fun getEmptyOneWeekSchedule(): OneWeekSchedule {
    return OneWeekSchedule(
        ScheduleWeekId(0, DateRange.parse("09.02.2024 - 11.02.2024")),
        days = listOf(),
    )
}
