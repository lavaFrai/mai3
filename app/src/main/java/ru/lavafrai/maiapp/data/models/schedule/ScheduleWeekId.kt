package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable
import ru.lavafrai.maiapp.data.models.DateRange


@Serializable
data class ScheduleWeekId(
    val number: Int,
    val range: DateRange
)

fun parseScheduleWeek(text: String): ScheduleWeekId {
    val number = text.split(" ").first().toInt()

    return ScheduleWeekId(
        number,
        DateRange.parse(text.removePrefix("$number "))
    )
}
