package ru.lavafrai.maiapp.ui.fragments

import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import ru.lavafrai.exler.mai.types.Teacher
import ru.lavafrai.mai.api.models.schedule.ScheduleDay
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.activities.MainActivity
import ru.lavafrai.maiapp.api.Api
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.localizers.localized
import ru.lavafrai.maiapp.data.localizers.toLocalizedDayMonthString
import ru.lavafrai.maiapp.ui.fragments.schedule.ScheduleDayView
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import kotlin.concurrent.thread

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ScheduleView(schedule: List<ScheduleDay>?) {
    val scheduleListState = rememberLazyListState()
    val context = LocalContext.current
    var teachersOnExler by remember { mutableStateOf<List<Teacher>>(listOf()) }

    thread {
        val tempTeachersOnExler = Api.getInstance().getExlerTeachers() ?: listOf()

        runBlocking {
            withContext(Dispatchers.Main) {
                teachersOnExler = tempTeachersOnExler
            }
        }
    }

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
                    schedule?.forEach { day ->
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
                                    text = day.date!!.toLocalizedDayMonthString(LocalContext.current),
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