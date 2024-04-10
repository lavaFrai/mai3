package ru.lavafrai.maiapp.data.models

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.BookSolid
import compose.icons.lineawesomeicons.Comment
import compose.icons.lineawesomeicons.TimesCircle
import compose.icons.lineawesomeicons.TimesSolid
import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.ui.fragments.IconBadge
import ru.lavafrai.maiapp.utils.LocalIcons


@Serializable
data class LessonAnnotation(
    val type: LessonAnnotationType,
    val lessonUid: Int,
    val data: String? = ""
) {
    companion object {
        val FinalTest = LessonAnnotationType("Зачет", 15)
        val ControlWork = LessonAnnotationType("Control Work", 10)
        val Colloquium = LessonAnnotationType("Colloquium", 9)
        val HomeWork = LessonAnnotationType("Home Work", 1, hasUserData = true)
        val Comment = LessonAnnotationType("Comment", 0, hasUserData = true)
        val Skipped = LessonAnnotationType("Skipped", 1)
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
        LessonAnnotation.FinalTest -> "З"
        LessonAnnotation.ControlWork -> "К"
        LessonAnnotation.HomeWork -> "Д"
        LessonAnnotation.Skipped -> "П"
        LessonAnnotation.Colloquium -> "Q"
        LessonAnnotation.Comment -> "i"
        else -> "Х"
    }
}

fun LessonAnnotation.color(): Color {
    return when(type) {
        LessonAnnotation.FinalTest -> Color(0xFFFF3F3F)
        LessonAnnotation.ControlWork -> Color(0xFFff5c5c)
        LessonAnnotation.Colloquium -> Color(0xffff8fab)
        LessonAnnotation.HomeWork -> Color(0xFFa594f9)
        LessonAnnotation.Comment -> Color(0xffbda88e)
        LessonAnnotation.Skipped -> Color(0xffccdad1)
        else -> Color(0xFFa594f9)
    }
}

fun LessonAnnotation.icon(): ImageVector {
    return when(type) {
        LessonAnnotation.FinalTest -> LocalIcons.FireWorkIcon
        LessonAnnotation.ControlWork -> LocalIcons.FireWorkIcon
        LessonAnnotation.Colloquium -> LocalIcons.FireWorkIcon
        LessonAnnotation.HomeWork -> LineAwesomeIcons.BookSolid
        LessonAnnotation.Comment -> LineAwesomeIcons.Comment
        LessonAnnotation.Skipped -> LineAwesomeIcons.TimesCircle
        else -> LineAwesomeIcons.TimesSolid
    }
}


@Composable
fun LessonAnnotation.View(modifier: Modifier = Modifier) {
    IconBadge(
        icon = this.icon(),
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
        LessonAnnotation.FinalTest -> stringResource(id = R.string.final_test)
        LessonAnnotation.ControlWork -> stringResource(id = R.string.control_work)
        LessonAnnotation.Colloquium -> stringResource(id = R.string.сolloquium)
        LessonAnnotation.HomeWork -> stringResource(id = R.string.home_work)
        LessonAnnotation.Comment -> stringResource(id = R.string.comment)
        else -> stringResource(id = R.string.unknown)
    }
}
