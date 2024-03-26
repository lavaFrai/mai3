package ru.lavafrai.maiapp.api

import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import ru.lavafrai.exler.mai.types.Teacher
import ru.lavafrai.exler.mai.types.TeacherInfo
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.maiapp.data.API_URL
import ru.lavafrai.maiapp.data.COOKIES_CONSTANT
import ru.lavafrai.maiapp.data.Settings

class Api {
    private val jsonParser = Json{ignoreUnknownKeys = true}

    fun getGroupsListOrNull(): List<Group>? {
        return getEndpoint("/groups")
    }

    fun getGroupScheduleOrNull(group: Group): Schedule? {
        return getEndpoint("/schedule/${group.name}")
    }

    fun getExlerTeachers(): List<Teacher>? {
        return getEndpoint("/exler-teachers")
    }

    fun getTeachers(): List<TeacherId>? {
        return getEndpoint("/teachers")
    }

    fun getTeacherInfo(teacher: Teacher): TeacherInfo? {
        return getEndpoint("/exler-teacher/${teacher.name}")
    }

    private inline fun <reified T>getEndpoint(endpoint: String): T? {
        return try {
            jsonParser.decodeFromString<T>(getEndpointAsPlainText(endpoint)!!)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun getEndpointAsPlainText(endpoint: String): String? {
        if (!Settings.isUseServerCache()) return null

        return try {
            val response = Jsoup
                .connect("$API_URL/$endpoint")
                .timeout(600 * 1000)
                .header("Cookie", COOKIES_CONSTANT)
                .validateTLSCertificates(false)
                .execute()
                .body()

            response
        } catch (e: Exception) {
            e.printStackTrace()
            null;
        }
    }

    companion object {
        private var instance: Api? = null

        fun getInstance(): Api {
            if (instance == null) {
                instance = Api()
            }

            return instance!!
        }
    }
}