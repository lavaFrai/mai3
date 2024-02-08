package ru.lavafrai.maiapp.ui.fragments.schedule

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.lavafrai.maiapp.data.getSampleWeekSchedule
import ru.lavafrai.maiapp.data.models.schedule.OneWeekSchedule

@Preview
@Composable
fun SchedulePageView(schedule: OneWeekSchedule = getSampleWeekSchedule()) {
    LazyColumn(content = {

    })
}