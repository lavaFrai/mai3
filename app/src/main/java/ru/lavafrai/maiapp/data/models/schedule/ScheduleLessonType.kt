package ru.lavafrai.maiapp.data.models.schedule

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.lavafrai.maiapp.R

enum class ScheduleLessonType {
    LECTURE,
    LABORATORY,
    SEMINAR,
    Unknown
}


@Composable
fun ScheduleLessonType.localized(): String {
    return when(this) {
        ScheduleLessonType.LECTURE -> stringResource(id = R.string.lecture)
        ScheduleLessonType.LABORATORY -> stringResource(id = R.string.laboratory)
        ScheduleLessonType.SEMINAR -> stringResource(id = R.string.seminar)
        else -> "Unk."
    }
}
