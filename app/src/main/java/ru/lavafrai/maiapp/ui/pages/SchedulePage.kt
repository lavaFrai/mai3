package ru.lavafrai.maiapp.ui.pages

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import ru.lavafrai.maiapp.GroupSelectActivity
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.models.group.GroupId
import ru.lavafrai.maiapp.data.models.schedule.OneWeekSchedule
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiapp.data.models.schedule.ScheduleWeekId
import ru.lavafrai.maiapp.ui.fragments.dialogs.NetworkErrorDialog
import ru.lavafrai.maiapp.ui.fragments.schedule.LoadingPageView
import ru.lavafrai.maiapp.ui.fragments.schedule.SchedulePageView


@Composable
fun SchedulePage(
    currentGroup: GroupId?,
    schedule: Schedule?,
    scheduleLoaded: Boolean?,
    subSchedule: MutableState<OneWeekSchedule?>,
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
        true -> SchedulePageView(schedule = schedule, subSchedule)
        null -> LoadingPageView()
        false -> NetworkErrorDialog(dialogShowed = true)
    }
}
