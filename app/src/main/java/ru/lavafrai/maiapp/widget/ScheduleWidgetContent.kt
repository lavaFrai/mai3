package ru.lavafrai.maiapp.widget

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.updateAll
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.activities.MainActivity
import ru.lavafrai.maiapp.api.LocalApi
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.models.LessonAnnotation
import ru.lavafrai.maiapp.widget.fragments.ScheduleWidgetContentList
import ru.lavafrai.maiapp.widget.fragments.ScheduleWidgetHeader
import ru.lavafrai.maiapp.widget.fragments.WidgetText


@Composable
fun ScheduleWidgetContent(context: Context) {
    val scheduleManager = ScheduleManager(LocalContext.current)

    Box (modifier = GlanceModifier
        .fillMaxSize()
        .background(WidgetColors.BACKGROUND)
        .padding(8.dp)
        .clickable( actionStartActivity<MainActivity>() )
    ) {
        if (scheduleManager.hasActualScheduleDownloaded()) {
            val schedule = scheduleManager.getActualSchedule()!!
            val annotations = LocalApi.getLessonAnnotations(context, Settings.getCurrentGroup()!!)

            WidgetScheduleView(context, schedule, annotations)
        } else {
            WidgetScheduleNotDownloaded(context)
        }
    }
}


@Composable
fun WidgetScheduleView(context: Context, schedule: Schedule, annotations: List<LessonAnnotation>) {
    Column {
        ScheduleWidgetHeader(context)
        Spacer(GlanceModifier.height(8.dp))
        ScheduleWidgetContentList(context, schedule, annotations)
    }
}

@Composable
fun WidgetScheduleNotDownloaded(context: Context) {
    Column(modifier = GlanceModifier
        .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WidgetText(text = context.resources.getString(R.string.widget_schedule_not_exist_1))
        WidgetText(text = context.resources.getString(R.string.widget_schedule_not_exist_2))
        Spacer(modifier = GlanceModifier.height(16.dp))
        Button(text = context.resources.getString(R.string.refresh), onClick = actionRunCallback<RefreshUnloadedScheduleAction>())
    }
}


class RefreshUnloadedScheduleAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {

        ScheduleWidget().updateAll(context)
        Log.i("ScheduleWidget", "Refreshing...")
    }
}