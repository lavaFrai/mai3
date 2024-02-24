package ru.lavafrai.maiapp.data

import android.content.Context
import kotlinx.serialization.json.Json
import ru.lavafrai.maiapp.api.Api
import ru.lavafrai.maiapp.data.models.group.GroupId
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiapp.data.parser.parseSchedule
import ru.lavafrai.maiapp.utils.decodeFromFile
import ru.lavafrai.maiapp.utils.encodeToFile
import java.io.File

class ScheduleManager(private val context: Context) {
    private fun hasScheduleDownloaded(groupId: GroupId): Boolean {
        val scheduleFile = File(context.getExternalFilesDir("schedule"), groupId.name)
        return scheduleFile.exists()
    }

    private fun getSchedule(groupId: GroupId): Schedule? {
        val scheduleFile = File(context.getExternalFilesDir("schedule"), groupId.name)

        return Json.decodeFromFile<Schedule>(scheduleFile)
    }

    fun getScheduleOrDownload(groupId: GroupId): Schedule? {
        return try {
            if (!hasScheduleDownloaded(groupId)) {
                downloadSchedule(groupId)
            }

            getSchedule(groupId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun deleteSchedule(groupId: GroupId?) {
        if (groupId == null) return

        val scheduleFile = File(context.getExternalFilesDir("schedule"), groupId.name)
        if (scheduleFile.exists()) {
            scheduleFile.delete()
        }
    }

    fun getActualSchedule(): Schedule? {
        return try {
            val settings = getSettings(context)
            if (!hasActualSchedule()) throw IllegalStateException("Have no actual schedule")
            if (!hasScheduleDownloaded(settings.currentGroup!!)) {
                downloadSchedule(settings.currentGroup!!)
            }

            getSchedule(settings.currentGroup!!)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun hasActualSchedule(): Boolean {
        val settings = getSettings(context)
        return settings.currentGroup != null;
    }

    fun hasActualScheduleDownloaded(): Boolean {
        val settings = getSettings(context)
        return hasScheduleDownloaded(settings.currentGroup!!);
    }

    fun idActualScheduleDownloaded(): Boolean {
        val settings = getSettings(context)
        if (!hasActualSchedule()) throw IllegalStateException("Have no actual schedule")

        return hasScheduleDownloaded(settings.currentGroup!!);
    }

    private fun downloadSchedule(groupId: GroupId) {
        val schedule = Api.getInstance().getGroupScheduleOrNull(groupId) ?: parseSchedule(groupId)

        val scheduleFile = File(context.getExternalFilesDir("schedule"), groupId.name)
        Json.encodeToFile(schedule, scheduleFile)
        Thread.sleep(100)
    }

    fun getDownloadedSchedulesList(): List<String> {
        val scheduleFile = context.getExternalFilesDir("schedule")
        return scheduleFile!!.listFiles()!!.map { it.name }
    }
}