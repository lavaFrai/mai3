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
import ru.lavafrai.maiapp.data.ScheduleManager
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
    ) {
        if (scheduleManager.hasActualScheduleDownloaded()) {
            WidgetScheduleView(context)
        } else {
            WidgetScheduleNotDownloaded()
        }
    }
}


@Composable
fun WidgetScheduleView(context: Context) {
    Column {
        ScheduleWidgetHeader(context)
        Spacer(GlanceModifier.height(8.dp))
        ScheduleWidgetContentList(context)
    }
}

@Composable
fun WidgetScheduleNotDownloaded() {
    Column(modifier = GlanceModifier
        .fillMaxSize()
        .background(WidgetColors.BACKGROUND),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WidgetText(text = "Schedule not downloaded yet")
        Spacer(modifier = GlanceModifier.height(8.dp))
        Button(text = "Refresh", onClick = actionRunCallback<RefreshUnloadedScheduleAction>())
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