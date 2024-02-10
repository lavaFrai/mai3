package ru.lavafrai.maiapp.ui.fragments.schedule

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.getSampleDaySchedule
import ru.lavafrai.maiapp.data.getSampleLessonSchedule
import ru.lavafrai.maiapp.data.models.schedule.OneDaySchedule
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiapp.data.models.schedule.ScheduleLesson
import ru.lavafrai.maiapp.data.models.schedule.localized
import ru.lavafrai.maiapp.ui.fragments.PairName
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.utils.localized

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
// @Preview
@Composable
fun SchedulePageView(schedule: Schedule) {
    val (currentSubSchedule, setCurrentSubSchedule) = remember { mutableStateOf(schedule.getCurrentSubScheduleOrNull()) }
    if (currentSubSchedule == null) {
        Text(text = "EMPTY WEEK")
        return
    }

    val (weekSelectorOpened, setWeekSelectorOpened) = rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val weekSelectorState = rememberModalBottomSheetState()
    val scheduleListState = rememberLazyListState()

    if (weekSelectorOpened) {
        WeekSelector(
            onClose = {
                scope.launch { weekSelectorState.hide() }
                    .invokeOnCompletion { setWeekSelectorOpened(false) }
            },
            onSelect = { selectedWeek ->
                setCurrentSubSchedule(schedule.subSchedules.find { it.weekId == selectedWeek })
                if (selectedWeek.range.isNow()) scope.launch {

                }
                else scope.launch { scheduleListState.scrollToItem(0) }
            },
            weeks = schedule.getWeeks()
        )
    }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp, 16.dp)
                .fillMaxWidth(),
        ) {
            TextH3(text = stringResource(id = R.string.schedule))
            TextButton(onClick = { setWeekSelectorOpened(true) }) {
                Text(text = stringResource(id = R.string.select_week))
            }
        }

        Box(
            modifier = Modifier
                .height(0.5.dp)
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn(state = scheduleListState) {
            currentSubSchedule.days.forEach { day ->
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


@Preview
@Composable
fun ScheduleDayView(modifier: Modifier = Modifier, day: OneDaySchedule = getSampleDaySchedule()) {
    Column(modifier.padding(8.dp)) {
        day.lessons.forEach { lesson ->
            ScheduleLessonView(lesson)
        }

    }
}


@Preview
@Composable
fun ScheduleLessonView(lesson: ScheduleLesson = getSampleLessonSchedule()) {
    Card(modifier = Modifier
        .padding(8.dp)
    ) {
        Column (modifier = Modifier.clickable {  }) {

            Row() {
                PairName(
                    modifier = Modifier.padding(8.dp),
                    text = lesson.getPairNumber().toString()
                )
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            lesson.name,
                            modifier = Modifier
                                .weight(1f)
                                .padding(bottom = 8.dp),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                lineHeight = 20.sp,
                            ),
                            color = MaterialTheme.colorScheme.onBackground,

                            )
                    }
                    Row {
                        Text(text = lesson.timeRange, style = MaterialTheme.typography.bodySmall)
                    }
                    Row {
                        Text(text = lesson.teacher, style = MaterialTheme.typography.bodySmall)
                    }

                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(8.dp, 0.dp),
            ) {
                Text(
                    text = lesson.location,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f)
                )
                /*SuggestionChip(
                    onClick = {},
                    label = { Text(text = lesson.location) },
                    icon = { Icon(Icons.Default.LocationOn, null)
                    })*/
                SuggestionChip(
                    onClick = {},
                    label = { Text(text = lesson.type.localized().uppercase()) })
            }
        }
    }
}
