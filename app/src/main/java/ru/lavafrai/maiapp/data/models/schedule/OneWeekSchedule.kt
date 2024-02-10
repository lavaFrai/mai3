package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable
import ru.lavafrai.maiapp.data.models.DateRange
import java.util.Calendar


@Serializable
data class OneWeekSchedule (
    val weekId: ScheduleWeekId,
    val days: List<OneDaySchedule>,
) {
    fun getTodayNumberOrInf(): Int {
        val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        for ((todayNumber, day) in days.withIndex()) {
            if (day.dayOfWeek.value >= today) return todayNumber
        }
        return 8
    }
}

fun getEmptyOneWeekSchedule(): OneWeekSchedule {
    return OneWeekSchedule(
        ScheduleWeekId(0, DateRange.parse("09.02.2024 - 11.02.2024")),
        days = listOf(),
    )
}
