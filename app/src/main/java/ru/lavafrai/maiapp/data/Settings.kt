package ru.lavafrai.maiapp.data

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.models.group.GroupId
import ru.lavafrai.maiapp.utils.decodeFromFile
import ru.lavafrai.maiapp.utils.encodeToFile
import java.io.File


@Serializable
data class Settings (
    var currentGroup: GroupId? = null
) {
    fun save(context: Context) {
        val settingsFile = File(context.getExternalFilesDir(null), context.getString(R.string.settings_file))

        Json.encodeToFile(this, settingsFile)
    }
}

fun getSettings(context: Context): Settings {
    val settingsFile = File(context.getExternalFilesDir(null), context.getString(R.string.settings_file))
    if (!settingsFile.exists()) createSettings(context)

    return Json.decodeFromFile<Settings>(settingsFile)
}

fun createSettings(context: Context) {
    val settingsFile = File(context.getExternalFilesDir(null), context.getString(R.string.settings_file))

    Json.encodeToFile(Settings(), settingsFile)
}
