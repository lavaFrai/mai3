package ru.lavafrai.maiapp.ui.fragments.pages

import android.app.Activity
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import io.appmetrica.analytics.AppMetrica
import ru.lavafrai.maiapp.GroupSelectActivity
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiapp.data.models.schedule.getEmptySchedule
import ru.lavafrai.maiapp.ui.fragments.dialogs.NetworkErrorDialog
import ru.lavafrai.maiapp.ui.fragments.schedule.LoadingPageView
import ru.lavafrai.maiapp.ui.fragments.schedule.SchedulePageView
import kotlin.concurrent.thread


@Preview
@Composable
fun SchedulePage() {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    val scheduleManager = ScheduleManager(LocalContext.current)
    val (scheduleLoaded, setScheduleLoaded) = remember { mutableStateOf(false) }
    val (schedule, setSchedule) = remember { mutableStateOf<Schedule?>(getEmptySchedule()) }

    var networkError by rememberSaveable { mutableStateOf(false) }

    if (!scheduleManager.hasActualSchedule()) {
        activity.startActivity(Intent(context, GroupSelectActivity::class.java))
        activity.finish()

        return
    }



    thread {
        try {
            setSchedule(scheduleManager.getActualSchedule())
            setScheduleLoaded(true)
        } catch (e: Exception) {
            e.printStackTrace()
            networkError = true
            AppMetrica.reportError("Main activity network error", e)
        }
    }
    
    NetworkErrorDialog(dialogShowed = networkError)

    AnimatedVisibility(
        visible = scheduleLoaded,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        SchedulePageView(schedule)
    }
    AnimatedVisibility(
        visible = !scheduleLoaded,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        LoadingPageView()
    }
}
