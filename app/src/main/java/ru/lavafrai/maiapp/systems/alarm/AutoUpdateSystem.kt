package ru.lavafrai.maiapp.systems.alarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import ru.lavafrai.maiapp.systems.MaiAppSystem
import java.util.Calendar

class AutoUpdateSystem: MaiAppSystem() {
    @SuppressLint("ScheduleExactAlarm")
    override fun init(context: Context) {
        super.init(context)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = getAlarmIntent(context)

        val time = Calendar.getInstance()
        time.add(Calendar.SECOND, 5)

        alarmManager.cancel(intent)
        alarmManager.setRepeating(AlarmManager.RTC, time.timeInMillis, 60000, intent)
    }

    fun getAlarmIntent(context: Context): PendingIntent {
        val intent = Intent(context, AutoUpdateReceiver::class.java)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }
}