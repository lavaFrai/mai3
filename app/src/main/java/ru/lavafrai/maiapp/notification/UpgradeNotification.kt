package ru.lavafrai.maiapp.notification

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.PROJECT_TELEGRAM_URL

fun getUpgradeNotification(context: Context, versionName: String): Notification {
    /*val intent = Intent(context, UpgradeActivity::class.java)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE)*/

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(PROJECT_TELEGRAM_URL))
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    return NotificationCompat.Builder(context, AppNotificationChannel.default)
        .setContentTitle(context.getString(R.string.upgrade_available))
        .setContentText(context.getString(R.string.upgrade_available_description))
        .setSubText(versionName)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentIntent(pendingIntent)
        .build()
}
