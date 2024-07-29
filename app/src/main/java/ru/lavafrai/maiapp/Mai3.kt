package ru.lavafrai.maiapp

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.systems.AppSystemName
import ru.lavafrai.maiapp.systems.MaiAppSystem
import ru.lavafrai.maiapp.systems.permissions.PermissionsSystem
import java.io.File


class Mai3 : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = AppMetricaConfig
            .newConfigBuilder(BuildConfig.APP_METRIKA_API_KEY)
            .withAppVersion(BuildConfig.VERSION_NAME)
            .withCrashReporting(true)
            .withLocationTracking(true)
            .build()
        AppMetrica.activate(this, config)

        Settings.init(this)
        filesPath = getExternalFilesDir(null)!!
        clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager;

        showToast = {
            Toast.makeText(this@Mai3, it, Toast.LENGTH_LONG).show()
        }

        systems = mapOf<AppSystemName, MaiAppSystem>(
            // AppSystemName.AUTO_UPDATE to AutoUpdateSystem(),
            // AppSystemName.NOTIFICATIONS to NotificationSystem(),
            AppSystemName.PERMISSIONS to PermissionsSystem(),
        )

        systems.forEach {
            it.value.init(this@Mai3)
        }
    }

    override fun onTerminate() {
        super.onTerminate()

    }

    companion object {
        lateinit var filesPath: File
        lateinit var clipboard: ClipboardManager
        lateinit var systems: Map<AppSystemName, MaiAppSystem>

        fun wipeData(path: File = filesPath) {
            path.listFiles()?.forEach {
                if (it.isDirectory) {
                    wipeData(it)
                }

                it.delete()
            }
        }

        fun restart(context: Context) {
            val packageManager = context.packageManager
            val intent = packageManager.getLaunchIntentForPackage(context.packageName)
            val componentName = intent!!.component
            val mainIntent = Intent.makeRestartActivityTask(componentName)
            // Required for API 34 and later
            // Ref: https://developer.android.com/about/versions/14/behavior-changes-14#safer-intents
            // Required for API 34 and later
            // Ref: https://developer.android.com/about/versions/14/behavior-changes-14#safer-intents
            mainIntent.setPackage(context.packageName)
            context.startActivity(mainIntent)
            Runtime.getRuntime().exit(0)
        }

        fun copyString(value: String) {
            val clip = ClipData.newPlainText(value, value)
            clipboard.setPrimaryClip(clip)
        }

        fun getSystem(system: AppSystemName): MaiAppSystem {
            return systems[system]!!
        }

        var showToast: (String) -> Unit = {}
    }
}