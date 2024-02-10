package ru.lavafrai.maiapp.ui.fragments.pages

import android.app.Activity
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ru.lavafrai.maiapp.GroupSelectActivity
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiapp.data.models.schedule.getEmptySchedule
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

    if (!scheduleManager.hasActualSchedule()) {
        activity.startActivity(Intent(context, GroupSelectActivity::class.java))
        activity.finish()

        return
    }

    thread {
        setSchedule(scheduleManager.getActualSchedule())
        setScheduleLoaded(true)
    }

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
