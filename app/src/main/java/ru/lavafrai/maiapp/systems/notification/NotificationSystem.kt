package ru.lavafrai.maiapp.systems.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import ru.lavafrai.maiapp.notification.AppNotificationChannel
import ru.lavafrai.maiapp.systems.MaiAppSystem

class NotificationSystem: MaiAppSystem() {
    @SuppressLint("ScheduleExactAlarm")
    override fun init(context: Context) {
        super.init(context)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channels = listOf(
            NotificationChannel(AppNotificationChannel.default, AppNotificationChannel.default, NotificationManager.IMPORTANCE_DEFAULT),
        )

        notificationManager.createNotificationChannels(channels)
    }
}