package ru.lavafrai.maiapp.api

import android.content.Context
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import ru.lavafrai.exler.mai.types.Teacher
import ru.lavafrai.exler.mai.types.TeacherInfo
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.mai.api.models.time.Date
import ru.lavafrai.mai.api.network.TolerantJson
import ru.lavafrai.maiapp.data.COOKIES_CONSTANT
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.models.LessonAnnotation
import ru.lavafrai.maiapp.data.models.LessonAnnotationType
import ru.lavafrai.maiapp.utils.decodeFromFile
import ru.lavafrai.maiapp.utils.encodeToFile
import java.io.File

object LocalApi {
    private val jsonParser = Json{ignoreUnknownKeys = true}
    const val annotationFilePostfix = ".annotations"

    fun getGroupsListOrNull(): List<Group>? {
        return getEndpoint("/groups")
    }

    fun getGroupScheduleOrNull(group: Group): Schedule? {
        return getEndpoint("/schedule/${group.name}")
    }

    fun getTeacherScheduleOrNull(group: Group): Schedule? {
        return getEndpoint("/teacher/schedule/${group.name}")
    }

    fun getExlerTeachers(): List<Teacher>? {
        return getEndpoint("/exler-teachers")
    }

    fun getTeachers(): List<TeacherId>? {
        return getEndpoint("/teachers")
    }

    fun getLessonAnnotations(context: Context, group: Group): List<LessonAnnotation> {
        val file = File(context.getExternalFilesDir("schedule"), group.name + annotationFilePostfix)
        if (!file.exists()) return listOf()

        return TolerantJson.decodeFromFile(file)
    }

    fun saveLessonAnnotations(context: Context, group: Group, annotations: List<LessonAnnotation>) {
        val file = File(context.getExternalFilesDir("schedule"), group.name + annotationFilePostfix)
        Json.encodeToFile(annotations, file)
    }

    fun addLessonAnnotation(context: Context, group: Group, day: Date, lesson: Int, type: LessonAnnotationType): MutableList<LessonAnnotation> {
        val annotations = getLessonAnnotations(context, group).toMutableList()
        annotations.add(LessonAnnotation(type, lesson))
        saveLessonAnnotations(context, group, annotations)
        return annotations
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
        val apiUrl = Settings.getApiUrl() ?: "https://mai3.lavafrai.ru/"

        return try {
            val response = Jsoup
                .connect("$apiUrl/$endpoint")
                .timeout(600 * 1000)
                .header("Cookie", COOKIES_CONSTANT)
                .execute()
                .body()

            response
        } catch (e: Exception) {
            e.printStackTrace()
            null;
        }
    }
}