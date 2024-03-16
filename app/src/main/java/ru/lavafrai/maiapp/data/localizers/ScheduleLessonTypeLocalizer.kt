package ru.lavafrai.maiapp.data.localizers

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.lavafrai.mai.api.models.schedule.LessonType
import ru.lavafrai.maiapp.R


@Composable
fun LessonType.localized(): String {
    return when(this) {
        LessonType.LECTURE -> stringResource(id = R.string.lecture)
        LessonType.LABORATORY -> stringResource(id = R.string.laboratory)
        LessonType.SEMINAR -> stringResource(id = R.string.seminar)
        LessonType.EXAM -> stringResource(id = R.string.exam)
        else -> "Unk."
    }
}

@Composable
fun LessonType.localizedShortcut(context: Context): String {
    return when(this) {
        LessonType.LECTURE -> context.resources.getString(R.string.lecture_shortcut)
        LessonType.LABORATORY -> context.resources.getString(R.string.laboratory_shortcut)
        LessonType.SEMINAR -> context.resources.getString(R.string.seminar_shortcut)
        LessonType.EXAM -> context.resources.getString(R.string.exam_shortcut)
        else -> "UNK"
    }
}
