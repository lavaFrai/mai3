package ru.lavafrai.maiapp.data.models.schedule

import kotlinx.serialization.Serializable
import ru.lavafrai.maiapp.data.models.group.GroupId


@Serializable
class Schedule (
    val groupId: GroupId,
    val subSchedules: List<OneWeekSchedule>
)
