package ru.lavafrai.maiapp.data.models

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.ui.fragments.schedule.PairNumber


@Serializable
data class LessonAnnotation(
    val type: LessonAnnotationType,
    val lessonUid: Int,
    val data: String? = ""
) {
    companion object {
        val ControlWork = LessonAnnotationType("Control Work", 10)
        val HomeWork = LessonAnnotationType("Home Work", 9, hasUserData = true)
        val Skipped = LessonAnnotationType("Skipped", 0)
    }
}

@Composable
fun Modifier.modifyByAnnotations(annotations: List<LessonAnnotation>): Modifier {
    var modifier = this

    annotations.sortedBy { it.type.priority }.forEach { annotation ->
        modifier = when (annotation.type) {
            LessonAnnotation.ControlWork -> {
                modifier
            }
            else -> modifier
        }
    }

    return modifier
}

fun List<LessonAnnotation>.toggle(lesson: Lesson, type: LessonAnnotationType): List<LessonAnnotation> {
    return if (any {it.lessonUid == lesson.getUid() && it.type == type}) {
        val newList = toMutableList()
        newList.filter { it.lessonUid != lesson.getUid() || it.type != type }
    } else {
        val newList = toMutableList()
        newList.add(LessonAnnotation(type, lesson.getUid()))
        newList
    }
}

fun List<LessonAnnotation>.getAnnotationOrNull(lesson: Lesson, type: LessonAnnotationType): LessonAnnotation? {
    return find { it.lessonUid == lesson.getUid() && it.type == type }
}

fun List<LessonAnnotation>.setAnnotationData(lesson: Lesson, type: LessonAnnotationType, data: String): List<LessonAnnotation> {
    val newList = toMutableList()
    newList.replaceAll { if (it.lessonUid == lesson.getUid() && it.type == type) it.copy(data = data) else it }
    return newList
}

fun List<LessonAnnotation>.isAnnotatedBy(lesson: Lesson, type: LessonAnnotationType): Boolean {
    return any {it.lessonUid == lesson.getUid() && it.type == type}
}

fun LessonAnnotation.letter(): String {
    return when(type) {
        LessonAnnotation.ControlWork -> "К"
        LessonAnnotation.HomeWork -> "Д"
        LessonAnnotation.Skipped -> "П"
        else -> "Х"
    }
}

fun LessonAnnotation.color(): Color {
    return when(type) {
        LessonAnnotation.ControlWork -> Color(0xFFff5c5c)
        LessonAnnotation.HomeWork -> Color(0xFF7fc8f8)
        LessonAnnotation.Skipped -> Color(0xffd1cfe2)
        else -> Color(0xFFa594f9)
    }
}


@Composable
fun LessonAnnotation.View(modifier: Modifier = Modifier) {
    PairNumber(
        text = letter(),
        background = color(),
        modifier = modifier,
        borderWidth = 1.dp,
        borderColor = MaterialTheme.colorScheme.surfaceVariant,
        bold = true,
    )
}

@Composable
fun LessonAnnotationType.localized(): String {
    return when (this) {
        LessonAnnotation.ControlWork -> stringResource(id = R.string.control_work)
        LessonAnnotation.HomeWork -> stringResource(id = R.string.home_work)
        else -> stringResource(id = R.string.unknown)
    }
}
