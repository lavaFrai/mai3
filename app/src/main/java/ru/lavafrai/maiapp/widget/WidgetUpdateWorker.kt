package ru.lavafrai.maiapp.widget

import android.content.Context
import androidx.glance.appwidget.updateAll
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class WidgetUpdateWorker(
    val context: Context,
    val params: WorkerParameters,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        ScheduleWidget().updateAll(context)
        return Result.success()
    }
}
