package ru.lavafrai.maiapp.data

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.models.group.GroupId
import ru.lavafrai.maiapp.utils.decodeFromFile
import ru.lavafrai.maiapp.utils.encodeToFile
import java.io.File


@Serializable
data class Settings (
    var currentGroup: GroupId? = null,
    var isDarkTheme: Boolean? = null,
    var isDynamicColoursEnabled: Boolean = true,
    var lastNotifiedUpgrade: String? = null
) {
    fun save() {
        val settingsFile = File(filesPath, settingsFileName)

        Json.encodeToFile(this, settingsFile)
    }

    companion object {
        private lateinit var filesPath: File
        private lateinit var settingsFileName: String

        fun init(context: Context) {
            filesPath = context.getExternalFilesDir(null)!!
            settingsFileName = context.getString(R.string.settings_file)
        }

        private fun getInstance(): Settings {
            val settingsFile = File(filesPath, settingsFileName)
            if (!settingsFile.exists())
            {
                val newSettings = File(filesPath, settingsFileName)
                Json.encodeToFile(Settings(), newSettings)
            }

            return Json.decodeFromFile<Settings>(settingsFile)
        }


        fun getCurrentGroup(): GroupId? {
            return Settings.getInstance().currentGroup
        }


        fun setCurrentGroup(newGroup: GroupId?) {
            val s = Settings.getInstance()
            s.currentGroup = newGroup
            s.save()
        }

        @Composable
        fun isDarkTheme(): Boolean {
            return getInstance().isDarkTheme ?: isSystemInDarkTheme()
        }

        fun getIsDarkTheme(): Boolean? {
            return getInstance().isDarkTheme
        }

        fun setIsDarkTheme(isDarkTheme: Boolean?) {
            val s = Settings.getInstance()
            s.isDarkTheme = isDarkTheme
            s.save()
        }

        fun isDynamicColors(): Boolean {
            return Settings.getInstance().isDynamicColoursEnabled
        }

        fun setDynamicColors(value: Boolean) {
            val settings = Settings.getInstance()
            settings.isDynamicColoursEnabled = value
            settings.save()
        }

        fun getLastNotifiedUpgrade(): String? {
            return Settings.getInstance().lastNotifiedUpgrade
        }

        fun setLastNotifiedUpgrade(value: String) {
            val settings = Settings.getInstance()
            settings.lastNotifiedUpgrade = value
            settings.save()
        }
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
