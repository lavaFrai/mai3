package ru.lavafrai.maiapp.activities.pages

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import ru.lavafrai.exler.mai.Exler
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.models.schedule.ScheduleDay
import ru.lavafrai.maiapp.activities.GroupSelectActivity
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.ui.fragments.dialogs.NetworkErrorDialog
import ru.lavafrai.maiapp.ui.fragments.schedule.LoadingPageView


@Composable
fun SchedulePage(
    currentGroup: Group?,
    schedule: Schedule?,
    scheduleLoaded: Boolean?,
    weekSchedule: MutableState<List<ScheduleDay>?>,
    exler: Exler,
) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    val scheduleManager = ScheduleManager(LocalContext.current)
    // val (scheduleLoaded, setScheduleLoaded) = remember { mutableStateOf(false) }
    // var schedule by remember{ mutableStateOf<Schedule?>(getEmptySchedule()) }
    val networkError = scheduleLoaded == false


    if (!scheduleManager.hasActualSchedule()) {
        activity.startActivity(Intent(context, GroupSelectActivity::class.java))
        activity.finish()

        return
    }


/*
    thread {
        Thread.sleep(10)

        try {
            schedule = (scheduleManager.getScheduleOrDownload(currentGroup!!))
            setScheduleLoaded(true)
        } catch (e: Exception) {
            e.printStackTrace()
            networkError = true
            AppMetrica.reportError("Main activity network error", e)
        }
    }*/

    NetworkErrorDialog(dialogShowed = networkError)

    /*
    AnimatedVisibility(
        visible = scheduleLoaded == true,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        SchedulePageView(schedule)
    }
    AnimatedVisibility(
        visible = scheduleLoaded == null,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        LoadingPageView()
    }*/
    
    when (scheduleLoaded) {
        true -> SchedulePageView(schedule = schedule, weekSchedule, exler)
        null -> LoadingPageView()
        false -> NetworkErrorDialog(dialogShowed = true)
    }
}