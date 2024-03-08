package ru.lavafrai.maiapp.data.localizers

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.lavafrai.mai.api.models.schedule.ScheduleLessonType
import ru.lavafrai.maiapp.R


@Composable
fun ScheduleLessonType.localized(): String {
    return when(this) {
        ScheduleLessonType.LECTURE -> stringResource(id = R.string.lecture)
        ScheduleLessonType.LABORATORY -> stringResource(id = R.string.laboratory)
        ScheduleLessonType.SEMINAR -> stringResource(id = R.string.seminar)
        ScheduleLessonType.EXAM -> stringResource(id = R.string.exam)
        else -> "Unk."
    }
}

@Composable
fun ScheduleLessonType.localizedShortcut(context: Context): String {
    return when(this) {
        ScheduleLessonType.LECTURE -> context.resources.getString(R.string.lecture_shortcut)
        ScheduleLessonType.LABORATORY -> context.resources.getString(R.string.laboratory_shortcut)
        ScheduleLessonType.SEMINAR -> context.resources.getString(R.string.seminar_shortcut)
        ScheduleLessonType.EXAM -> context.resources.getString(R.string.exam_shortcut)
        else -> "UNK"
    }
}
