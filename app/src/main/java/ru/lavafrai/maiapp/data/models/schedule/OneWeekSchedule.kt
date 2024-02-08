package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable

@Serializable
data class OneWeekSchedule (
    val weekId: ScheduleWeekId,
    val days: List<OneDaySchedule>,
)