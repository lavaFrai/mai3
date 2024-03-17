package ru.lavafrai.maiapp.ui.fragments.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lavafrai.exler.mai.types.Teacher
import ru.lavafrai.mai.api.models.schedule.ScheduleDay


@Composable
fun ScheduleDayView(modifier: Modifier = Modifier, day: ScheduleDay, exlerTeachers: List<Teacher> = listOf()) {
    Column(modifier.padding(8.dp)) {
        day.lessons.forEach { lesson ->
            ScheduleLessonView(lesson, exlerTeachers)
        }

    }
}