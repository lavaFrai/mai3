package ru.lavafrai.maiapp.ui.fragments.schedule

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiapp.ui.fragments.dialogs.ChangeWeekDialog
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.utils.localized

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
// @Preview
@Composable
fun SchedulePageView(schedule: Schedule?) {
    val (weekSelectorOpened, setWeekSelectorOpened) = rememberSaveable { mutableStateOf(false) }
    val (changeWeekDialogOpened, setChangeWeekDialogOpened) = rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    if (schedule == null) {
        ErrorScheduleView()
        return
    }

    val (currentSubSchedule, setCurrentSubSchedule) = remember { mutableStateOf(schedule.getCurrentSubScheduleOrNull()) }
    val scheduleListState = rememberLazyListState(
        initialFirstVisibleItemIndex = if (currentSubSchedule?.weekId?.range?.isNow() == true) getTodayIndex(
            schedule
        ) else 0
    )

    if (weekSelectorOpened) {
        WeekSelector(
            onClose = {
                setWeekSelectorOpened(false)
            },
            onSelect = { selectedWeek ->
                setCurrentSubSchedule(schedule.subSchedules.find { it.weekId == selectedWeek })

                if (selectedWeek.range.isNow()) scope.launch {
                    scrollToToday(schedule, scheduleListState)
                }
                else scope.launch { scheduleListState.scrollToItem(0) }
            },
            weeks = schedule.getWeeks()
        )
    }

    if (changeWeekDialogOpened) {
        ChangeWeekDialog(
            onClose = {
                setChangeWeekDialogOpened(false)
            },
            onSelect = { selectedWeek ->
                setCurrentSubSchedule(schedule.subSchedules.find { it.weekId == selectedWeek })
                if (selectedWeek == null) return@ChangeWeekDialog

                if (selectedWeek.range.isNow()) scope.launch {
                    scrollToToday(schedule, scheduleListState)
                }
                else scope.launch { scheduleListState.scrollToItem(0) }
            },
            weeks = schedule.getWeeks(),
            onOpenWeekSelector = {
                setWeekSelectorOpened(true)
            }
        )
    }

    if (currentSubSchedule == null) {
        Column {
            ScheduleHeader {
                setChangeWeekDialogOpened(true)
            }

            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Outlined.DateRange, null)
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = stringResource(id = R.string.empty_week), modifier = Modifier.fillMaxWidth(0.5f), textAlign = TextAlign.Center)
            }
        }
    }
    else {
        Column {
            ScheduleHeader {
                setChangeWeekDialogOpened(true)
            }

            LazyColumn(state = scheduleListState) {
                currentSubSchedule.days.forEach { day ->
                    item {}
                    stickyHeader {
                        Row(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextH3(text = day.dayOfWeek.localized().capitalize())
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = day.date,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                                fontWeight = FontWeight.Light
                            )
                        }
                    }

                    item {
                        ScheduleDayView(day = day)
                    }
                }
            }
        }
    }
}


@Composable
fun ErrorScheduleView() {
    Column {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp, 16.dp)
                    .fillMaxWidth(),
            ) {
                TextH3(text = stringResource(id = R.string.schedule))
            }

            Box(
                modifier = Modifier
                    .height(0.5.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Outlined.DateRange, null)
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.schedule_loading_error),
                modifier = Modifier.fillMaxWidth(0.5f),
                textAlign = TextAlign.Center
            )

        }
    }
    return
}


suspend fun scrollToToday(schedule: Schedule, scheduleListState: LazyListState) {
    scheduleListState.scrollToItem(
        getTodayIndex(schedule)
    )
}


fun getTodayIndex(schedule: Schedule): Int {
    return (schedule.getCurrentSubScheduleOrNull()?.getTodayNumberOrInf() ?: 0) * 2 + 1
}

