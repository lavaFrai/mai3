package ru.lavafrai.maiapp.activities.pages

import android.os.Looper
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.launch
import ru.lavafrai.exler.mai.Exler
import ru.lavafrai.exler.mai.types.Teacher
import ru.lavafrai.mai.api.models.schedule.OneWeekSchedule
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.activities.MainActivity
import ru.lavafrai.maiapp.api.Api
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.localizers.toLocalizedDayMonthString
import ru.lavafrai.maiapp.ui.fragments.PageTitle
import ru.lavafrai.maiapp.ui.fragments.dialogs.ChangeWeekDialog
import ru.lavafrai.maiapp.ui.fragments.schedule.ScheduleDayView
import ru.lavafrai.maiapp.ui.fragments.schedule.WeekSelector
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.utils.localized
import kotlin.concurrent.thread

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
// @Preview
@Composable
fun SchedulePageView(
    schedule: Schedule?,
    subSchedule: MutableState<OneWeekSchedule?>,
    exler: Exler
) {
    val (weekSelectorOpened, setWeekSelectorOpened) = rememberSaveable { mutableStateOf(false) }
    val (changeWeekDialogOpened, setChangeWeekDialogOpened) = rememberSaveable {
        mutableStateOf(
            false
        )
    }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var teachersOnExler by remember { mutableStateOf(listOf<Teacher>()) }
    thread {
        Thread.sleep(100)
        teachersOnExler = Api.getInstance().getTeachers() ?: listOf<Teacher>()
    }

    if (schedule == null) {
        ErrorScheduleView()
        return
    }

    val currentSubSchedule = subSchedule.value
    val setCurrentSubSchedule = { value: OneWeekSchedule? -> subSchedule.value = value }

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
            weeks = schedule.getWeeks(),
            openedWeekId = currentSubSchedule?.weekId
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

    PageTitle (
        stringResource(id = R.string.schedule),
        secondText = Settings.getCurrentGroup()?.name,
        padded = false,
        buttonText = stringResource(id = R.string.select_week),
        onButtonClicked = {setChangeWeekDialogOpened(true)}
    ) {
        if (currentSubSchedule == null) {
            Column {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Outlined.DateRange, null)
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = stringResource(id = R.string.empty_week),
                        modifier = Modifier.fillMaxWidth(0.5f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            Column() {
                Box {
                    var isRefreshing by rememberSaveable { mutableStateOf(false) }
                    val pullToRefreshState = rememberPullRefreshState(isRefreshing, {
                        thread {
                            isRefreshing = true

                            val group = Settings.getCurrentGroup() ?: return@thread

                            thread {
                                Looper.prepare()
                                val newSchedule = Api.getInstance().getGroupScheduleOrNull(group)
                                    ?: ScheduleManager(context).downloadScheduleOrNull(group)

                                if (newSchedule == null) {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.schedule_update_failed),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Log.i("APP", "Schedule updated")

                                    MainActivity.setSchedule(newSchedule)
                                }

                                isRefreshing = false
                            }

                        }
                    })
                    Box {
                        LazyColumn(
                            state = scheduleListState,
                            modifier = Modifier
                                .fillMaxSize()
                                .pullRefresh(pullToRefreshState)
                        ) {
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
                                            text = day.date.toLocalizedDayMonthString(LocalContext.current),
                                            color = MaterialTheme.colorScheme.onBackground.copy(
                                                alpha = 0.5f
                                            ),
                                            fontWeight = FontWeight.Light
                                        )
                                    }
                                }

                                item {
                                    ScheduleDayView(day = day, exlerTeachers = teachersOnExler)
                                }
                            }
                        }

                        PullRefreshIndicator(
                            refreshing = isRefreshing,
                            state = pullToRefreshState,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                        )
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
    return (schedule.getCurrentSubScheduleOrNull()?.getTodayNumberOrInf() ?: 0) * 3
}

