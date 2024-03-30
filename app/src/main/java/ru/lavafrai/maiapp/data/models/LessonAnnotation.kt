package ru.lavafrai.maiapp.data.models

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.maiapp.ui.fragments.schedule.PairNumber


@Serializable
data class LessonAnnotation(
    val type: LessonAnnotationType,
    val lessonUid: Int,
)

@Composable
fun Modifier.modifyByAnnotations(annotations: List<LessonAnnotation>): Modifier {
    var modifier = this

    annotations.sortedBy { it.type.priority }.forEach { annotation ->
        modifier = when (annotation.type) {
            LessonAnnotationTypes.ControlWork -> {
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
        newList.remove(LessonAnnotation(type, lesson.getUid()))
        newList
    } else {
        val newList = toMutableList()
        newList.add(LessonAnnotation(type, lesson.getUid()))
        newList
    }
}

fun List<LessonAnnotation>.isAnnotatedBy(lesson: Lesson, type: LessonAnnotationType): Boolean {
    return any {it.lessonUid == lesson.getUid() && it.type == type}
}

fun LessonAnnotation.letter(): String {
    return when(type) {
        LessonAnnotationTypes.ControlWork -> "К"
        LessonAnnotationTypes.Skipped -> "П"
        else -> "Х"
    }
}

fun LessonAnnotation.color(): Color {
    return when(type) {
        LessonAnnotationTypes.ControlWork -> Color(0xFFF44336)
        LessonAnnotationTypes.Skipped -> Color(0xFF2196F3)
        else -> Color(0xFF9C27B0)
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