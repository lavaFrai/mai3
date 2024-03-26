package ru.lavafrai.maiapp.data

import android.content.Context
import kotlinx.serialization.json.Json
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.parser.parseSchedule
import ru.lavafrai.maiapp.api.Api
import ru.lavafrai.maiapp.utils.decodeFromFile
import ru.lavafrai.maiapp.utils.encodeToFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class ScheduleManager(private val context: Context) {
    private fun hasScheduleDownloaded(group: Group): Boolean {
        val scheduleFile = File(context.getExternalFilesDir("schedule"), group.name)
        return scheduleFile.exists()
    }

    private fun getSchedule(group: Group): Schedule? {
        val scheduleFile = File(context.getExternalFilesDir("schedule"), group.name)

        return Json.decodeFromFile<Schedule>(scheduleFile)
    }

    fun getScheduleSize(group: Group): Long {
        return Files.size( Paths.get( File(context.getExternalFilesDir("schedule"), group.name).path ))
    }

    fun getScheduleOrDownload(group: Group): Schedule? {
        return try {
            if (!hasScheduleDownloaded(group)) {
                downloadSchedule(group)
            }

            getSchedule(group)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun deleteSchedule(group: Group?) {
        if (group == null) return

        val scheduleFile = File(context.getExternalFilesDir("schedule"), group.name)
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

    fun getActualScheduleOrNull(): Schedule? {
        return try {
            val settings = getSettings(context)
            if (!hasActualSchedule()) throw IllegalStateException("Have no actual schedule")

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

    fun downloadSchedule(group: Group) {
        val schedule = Api.getInstance().getGroupScheduleOrNull(group) ?: parseSchedule(group)

        val scheduleFile = File(context.getExternalFilesDir("schedule"), group.name)
        Json.encodeToFile(schedule, scheduleFile)
    }

    fun downloadScheduleOrNull(group: Group): Schedule? {
        return try {
            val schedule =
                Api.getInstance().getGroupScheduleOrNull(group) ?: parseSchedule(group)

            val scheduleFile = File(context.getExternalFilesDir("schedule"), group.name)
            Json.encodeToFile(schedule, scheduleFile)

            schedule
        } catch (e: Exception) {
            null
        }
    }

    fun getDownloadedSchedulesList(): List<String> {
        val scheduleFile = context.getExternalFilesDir("schedule")
        return scheduleFile!!.listFiles()!!.map { it.name }
    }
}