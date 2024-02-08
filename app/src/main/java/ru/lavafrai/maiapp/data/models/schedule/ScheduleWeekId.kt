package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable


@Serializable
data class ScheduleWeekId(
    val number: Int,
    val rangeLabel: String = ""
)

fun parseScheduleWeek(text: String): ScheduleWeekId {
    val number = text.split(" ").first().toInt()

    return ScheduleWeekId(
        number,
        text.removePrefix("$number ")
    )
}
