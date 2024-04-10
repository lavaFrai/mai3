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
import ru.lavafrai.maiapp.activities.StartActivity
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
    val networkError = scheduleLoaded == false


    if (!scheduleManager.hasActualSchedule()) {
        val intent = Intent(context, StartActivity::class.java)
        // intent.putExtra(SearchActivity.ExtraKeys.Target, GroupSelectActivity.ReturnType.AddNewGroupAndOpenMainActivity)
        activity.startActivity(intent)
        activity.finish()

        return
    }

    NetworkErrorDialog(dialogShowed = networkError)
    
    when (scheduleLoaded) {
        true -> ScheduleView(schedule = schedule, weekSchedule, exler)
        null -> LoadingPageView()
        false -> NetworkErrorDialog(dialogShowed = true)
    }
}
