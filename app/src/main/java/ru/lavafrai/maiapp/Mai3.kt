package ru.lavafrai.maiapp

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import ru.lavafrai.maiapp.data.Settings
import java.io.File


class Mai3 : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.APP_METRIKA_API_KEY != null) {
            val config = AppMetricaConfig
                .newConfigBuilder(BuildConfig.APP_METRIKA_API_KEY)
                .withAppVersion(getString(R.string.version))
                .withCrashReporting(true)
                .withLocationTracking(true)
                .build()
            AppMetrica.activate(this, config)
        }

        Settings.init(this)
        filesPath = getExternalFilesDir(null)!!
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager;
    }

    override fun onTerminate() {
        super.onTerminate()

    }

    companion object {
        lateinit var filesPath: File
        lateinit var clipboard: ClipboardManager

        fun wipeData(path: File = filesPath) {
            path.listFiles()?.forEach {
                if (it.isDirectory) {
                    wipeData(it)
                }

                it.delete()
            }
        }

        fun copyString(value: String) {
            val clip = ClipData.newPlainText(value, value)
            clipboard.setPrimaryClip(clip)
        }
    }
}