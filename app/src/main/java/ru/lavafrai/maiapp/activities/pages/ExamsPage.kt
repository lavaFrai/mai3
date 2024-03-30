package ru.lavafrai.maiapp.activities.pages

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.lavafrai.exler.mai.Exler
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.mai.api.models.schedule.LessonType
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.models.schedule.ScheduleDay
import ru.lavafrai.mai.api.models.time.Date
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.api.LocalApi
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.models.LessonAnnotation
import ru.lavafrai.maiapp.data.models.LessonAnnotationTypes
import ru.lavafrai.maiapp.data.models.isAnnotatedBy
import ru.lavafrai.maiapp.ui.fragments.ModalBottomDialog
import ru.lavafrai.maiapp.ui.fragments.PageTitle
import ru.lavafrai.maiapp.ui.fragments.schedule.ScheduleView
import ru.lavafrai.maiapp.ui.fragments.text.NetworkLoadingView
import ru.lavafrai.maiapp.ui.fragments.text.TextH3


private val work_type_filterers = listOf<Triple<(Lesson, List<LessonAnnotation>) -> Boolean, Int, MutableState<Boolean>>>(
    Triple({ lesson, _ -> lesson.type == LessonType.EXAM }, R.string.exam, mutableStateOf(true)),
    Triple({ lesson, _ -> lesson.type == LessonType.LABORATORY }, R.string.laboratory, mutableStateOf(true)),
    Triple({ lesson, annotations -> annotations.isAnnotatedBy(lesson, LessonAnnotationTypes.ControlWork) }, R.string.control_work, mutableStateOf(true)),
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamsPage(
    currentGroup: Group?,
    schedule: Schedule?,
    scheduleLoaded: Boolean?,
    weekSchedule: MutableState<List<ScheduleDay>?>,
    exler: Exler,
) {
    val context = LocalContext.current
    val filterDialogState = rememberSaveable { mutableStateOf(false) }
    var examsSchedule by remember { mutableStateOf(filterExamsSchedule(context, schedule)) }
    val annotations = remember { LocalApi.getLessonAnnotations(context, Settings.getCurrentGroup()!!) }

    ModalBottomDialog (filterDialogState) {
        LazyColumn () {
            item {
                TextH3(
                    text = stringResource(id = R.string.work_type),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }

            items(work_type_filterers) { week ->
                Row(
                    modifier = Modifier
                        .clickable {
                            week.third.value = !week.third.value; examsSchedule =
                            filterExamsSchedule(context, schedule)
                        }
                        .fillMaxWidth()
                        .padding(0.dp),
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    /*Icon(
                        Icons.Default.Check,
                        null,
                        Modifier.padding(16.dp).width(30.dp),
                        tint = if (week.third.value) MaterialTheme.colorScheme.primary else Color.Transparent
                    )*/

                    Column (
                        Modifier
                            .width(54.dp)
                            .height(54.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Checkbox(
                            checked = week.third.value,
                            onCheckedChange = {week.third.value = !week.third.value; examsSchedule = filterExamsSchedule(context, schedule)},
                        )
                    }

                    Text(text = stringResource(id = week.second).capitalize())
                }
            }
        }
    }

    PageTitle (
        stringResource(id = R.string.exams),
        secondText = Settings.getCurrentGroup()?.name,
        padded = false,
        buttonText = stringResource(id = R.string.work_type),
        onButtonClicked = {
            filterDialogState.value = true
        }
    ) {
        val context = LocalContext.current
        when (schedule) {
            null -> NetworkLoadingView()
            else -> ScheduleView(schedule = examsSchedule)
        }
    }
}

fun filterExamsSchedule(context: Context, schedule: Schedule?): List<ScheduleDay>? {
    val annotations = LocalApi.getLessonAnnotations(context, Settings.getCurrentGroup()!!)

    return schedule?.days?.map {
        it.copy(lessons = it.lessons.filter { lesson ->
            work_type_filterers.any { if (it.third.value) it.first(lesson, annotations) else false }
        })
    }?.filter {
        it.date?.isEarlierThan(Date.now())?.not() ?: true && it.lessons.isNotEmpty()
    }
}