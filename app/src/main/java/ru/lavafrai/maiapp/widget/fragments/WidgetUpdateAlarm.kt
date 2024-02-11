package ru.lavafrai.maiapp.widget.fragments

import android.app.AlarmManager
import android.content.Context

class WidgetUpdateAlarm {
}

fun enableAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    throw NotImplementedError()
    // alarmManager.setExactAndAllowWhileIdle()
}