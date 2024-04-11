package ru.lavafrai.maiapp.ui.fragments.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lavafrai.exler.mai.types.Teacher
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.mai.api.models.schedule.ScheduleDay
import ru.lavafrai.mai.api.models.schedule.TeacherId
import ru.lavafrai.mai.api.models.time.Date
import ru.lavafrai.maiapp.data.models.LessonAnnotation


@Composable
fun ScheduleDayView(
    modifier: Modifier = Modifier,
    day: ScheduleDay,
    exlerTeachers: List<Teacher> = listOf(),
    knownTeachers: List<TeacherId> = listOf(),
    annotations: List<LessonAnnotation> = listOf(),
    onOpenAnnotationControls: (Date, Lesson) -> Unit,
) {
    val uids = day.lessons.map { it.getUid() }
    val dayAnnotations = annotations.filter { it.lessonUid in uids }

    Column(modifier.padding(8.dp)) {
        day.lessons.sortedBy { it.getPairNumber() }.forEach { lesson ->
            lesson.View(
                exlerTeachers,
                knownTeachers,
                dayAnnotations.filter { it.lessonUid == lesson.getUid() },
                onOpenAnnotationControls = {onOpenAnnotationControls(day.date!!, lesson)}
            )
        }
    }
}