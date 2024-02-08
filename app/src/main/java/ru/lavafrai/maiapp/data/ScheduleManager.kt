package ru.lavafrai.maiapp.data

import android.content.Context
import kotlinx.serialization.json.Json
import ru.lavafrai.maiapp.data.models.schedule.GroupId
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiapp.data.parser.parseSchedule
import ru.lavafrai.maiapp.utils.decodeFromFile
import ru.lavafrai.maiapp.utils.encodeToFile
import java.io.File

class ScheduleManager(private val context: Context) {
    fun hasSchedule(groupId: GroupId): Boolean {
        val scheduleFile = File(context.getExternalFilesDir("schedule"), groupId.name)
        return scheduleFile.exists()
    }

    private fun getSchedule(groupId: GroupId): Schedule? {
        val scheduleFile = File(context.getExternalFilesDir("schedule"), groupId.name)

        return Json.decodeFromFile<Schedule>(scheduleFile)
    }

    fun getActualSchedule(): Schedule {
        val settings = getSettings(context)
        if (!hasActualSchedule()) throw IllegalStateException("Have no actual schedule")
        if (!hasSchedule(settings.currentGroup!!)) throw IllegalStateException("Cant find no actual schedule");

        return getSchedule(settings.currentGroup!!)!!;
    }

    private fun hasActualSchedule(): Boolean {
        val settings = getSettings(context)
        return settings.currentGroup != null;
    }

    fun idActualScheduleDownloaded(): Boolean {
        val settings = getSettings(context)
        if (!hasActualSchedule()) throw IllegalStateException("Have no actual schedule")

        return hasSchedule(settings.currentGroup!!);
    }

    fun downloadSchedule(groupId: GroupId) {
        val schedule = parseSchedule(groupId)
        val scheduleFile = File(context.getExternalFilesDir("schedule"), groupId.name)
        Json.encodeToFile(schedule, scheduleFile)
    }
}