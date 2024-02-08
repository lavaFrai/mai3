package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable


@Serializable
class Schedule (
    val groupId: GroupId,
    val subSchedules: List<OneWeekSchedule>
)
