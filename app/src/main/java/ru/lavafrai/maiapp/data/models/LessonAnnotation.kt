package ru.lavafrai.maiapp.data.models

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import ru.lavafrai.mai.api.models.schedule.Lesson


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
                modifier.background(MaterialTheme.colorScheme.secondaryContainer)
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