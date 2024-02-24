package ru.lavafrai.maiapp.api

import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import ru.lavafrai.maiapp.data.API_URL
import ru.lavafrai.maiapp.data.COOKIES_CONSTANT
import ru.lavafrai.maiapp.data.models.group.GroupId
import ru.lavafrai.maiapp.data.models.schedule.Schedule

class Api {
    fun getGroupsListOrNull(): List<GroupId>? {
        return getEndpoint("/groups")
    }

    fun getGroupScheduleOrNull(group: GroupId): Schedule? {
        return getEndpoint("/schedule/${group.name}")
    }

    private inline fun <reified T>getEndpoint(endpoint: String): T? {
        return try {
            Json.decodeFromString<T>(getEndpointAsPlainText(endpoint)!!)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun getEndpointAsPlainText(endpoint: String): String? {
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