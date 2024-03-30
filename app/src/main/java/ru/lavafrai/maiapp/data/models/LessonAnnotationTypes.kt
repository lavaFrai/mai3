package ru.lavafrai.maiapp.data.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.serialization.Serializable
import ru.lavafrai.maiapp.R

object LessonAnnotationTypes {
    val ControlWork = LessonAnnotationType("Control Work", 10)
    val Skipped = LessonAnnotationType("Skipped", 9)
}

@Serializable
data class LessonAnnotationType (
    val name: String,
    val priority: Long,
) {
    @Composable
    fun localized(): String {
        return when (this) {
            LessonAnnotationTypes.ControlWork -> stringResource(id = R.string.control_work)
            else -> stringResource(id = R.string.unknown)
        }
    }
}