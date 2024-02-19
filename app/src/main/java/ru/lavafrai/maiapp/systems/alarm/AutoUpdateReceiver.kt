package ru.lavafrai.maiapp.systems.alarm

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import io.appmetrica.analytics.AppMetrica
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import ru.lavafrai.maiapp.BuildConfig
import ru.lavafrai.maiapp.data.ACTUAL_VERSION_INFO_URL
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.models.ActualVersionInfo
import ru.lavafrai.maiapp.notification.getUpgradeNotification
import kotlin.concurrent.thread


class AutoUpdateReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        thread {
            Log.i(javaClass.name, "Upgrade check started, actual: ${BuildConfig.VERSION_NAME}, lastNotified = ${Settings.getLastNotifiedUpgrade()}")
            try {
                val request = Jsoup
                    .connect(ACTUAL_VERSION_INFO_URL)
                    .validateTLSCertificates(false)
                    .ignoreContentType(true)
                    .execute()
                Log.i(javaClass.name, request.body())
                val upgradeInfo = Json.decodeFromString<ActualVersionInfo>(request.body())

                if (upgradeInfo.actual != BuildConfig.VERSION_NAME) {
                    if (Settings.getLastNotifiedUpgrade() != upgradeInfo.actual) {
                        sendUpgradeReadyNotification(context, upgradeInfo.actual)
                        Log.i(javaClass.name, "Upgrade notified")
                        Settings.setLastNotifiedUpgrade(upgradeInfo.actual)
                    }
                    else {
                        Log.i(javaClass.name, "Upgrade already notified")
                    }
                }
                else {
                    Log.i(javaClass.name, "No upgrades available")
                }
            } catch(e: Exception) {
                e.printStackTrace()
                try { AppMetrica.reportError("Upgrade check error", e) } finally {}
            }
        }
    }

    fun sendUpgradeReadyNotification(context: Context, versionName: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(1, getUpgradeNotification(context, versionName))
    }
}