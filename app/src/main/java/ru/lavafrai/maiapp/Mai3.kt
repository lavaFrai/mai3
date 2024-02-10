package ru.lavafrai.maiapp

import android.app.Application
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import ru.lavafrai.maiapp.data.Settings
import java.io.File

class Mai3 : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = AppMetricaConfig
            .newConfigBuilder(BuildConfig.APP_METRIKA_API_KEY)
            .withAppVersion(getString(R.string.version))
            .withCrashReporting(true)
            .withLocationTracking(true)
            .build()
        AppMetrica.activate(this, config)

        Settings.init(this)
        filesPath = getExternalFilesDir(null)!!
    }

    companion object {
        lateinit var filesPath: File

        fun wipeData(path: File = filesPath) {
            path.listFiles()?.forEach {
                if (it.isDirectory) {
                    wipeData(it)
                }

                it.delete()
            }
        }
    }
}