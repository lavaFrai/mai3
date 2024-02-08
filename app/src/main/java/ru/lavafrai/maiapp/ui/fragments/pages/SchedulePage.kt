package ru.lavafrai.maiapp.ui.fragments.pages

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ru.lavafrai.maiapp.GroupSelectActivity
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.models.schedule.getEmptyOneWeekSchedule
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
    val (schedule, setSchedule) = remember { mutableStateOf(getEmptySchedule()) }

    if (!scheduleManager.hasActualSchedule()) {
        activity.startActivity(Intent(context, GroupSelectActivity::class.java))
        activity.finish()

        return
    }

    thread {
        setSchedule(scheduleManager.getActualSchedule())
        setScheduleLoaded(true)
    }

    when {
        scheduleLoaded -> SchedulePageView(schedule.getWeek(0) ?: getEmptyOneWeekSchedule())
        else -> LoadingPageView()
    }
}
