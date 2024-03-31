package ru.lavafrai.maiapp.ui.fragments.schedule

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.lavafrai.exler.mai.types.Teacher
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.maiapp.activities.TeacherActivity
import ru.lavafrai.maiapp.data.localizers.localized
import ru.lavafrai.maiapp.data.models.LessonAnnotation
import ru.lavafrai.maiapp.data.models.View
import ru.lavafrai.maiapp.data.models.modifyByAnnotations
import ru.lavafrai.maiapp.ui.theme.MaiColor
import ru.lavafrai.maiapp.utils.fullNameEquals
import ru.lavafrai.maiapp.utils.longClickable


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleLessonView(
    lesson: Lesson,
    exlerTeachers: List<Teacher> = listOf(),
    annotations: List<LessonAnnotation> = listOf(),
    onOpenAnnotationControls: (Int) -> Unit
) {
    val context = LocalContext.current
    val haptic = LocalHapticFeedback.current

    Card(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .longClickable {
                    onOpenAnnotationControls(lesson.getPairNumber())
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
                .modifyByAnnotations(annotations)
        ) {
            Row() {
                Column {
                    PairNumber(
                        modifier = Modifier.padding(8.dp),
                        text = lesson.getPairNumber().toString()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LessonAnnotationsView(annotations) { onOpenAnnotationControls(lesson.getPairNumber()) }
                }
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            lesson.name,
                            modifier = Modifier
                                .weight(1f)
                                .padding(bottom = 8.dp),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                lineHeight = 20.sp,
                            ),
                            color = MaterialTheme.colorScheme.onBackground,

                            )
                    }
                    /* Teacher name */
                    Row {
                        Column {
                            lesson.lectors.forEach { lector ->
                                var teacherFound by rememberSaveable { mutableStateOf(false) }
                                val teacher =
                                    exlerTeachers.find { it.name.fullNameEquals(lector.name) }

                                teacherFound = teacher != null
                                Text(
                                    text = lector.name,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (teacherFound) MaiColor else Color.Unspecified,
                                    modifier = if (teacherFound) {
                                        Modifier.clickable {
                                            val intent =
                                                Intent(context, TeacherActivity::class.java)
                                            intent.putExtra("teacher", Json.encodeToString(teacher))
                                            context.startActivity(intent)
                                            // CustomTabs.openTab(context, "https://mai-exler.ru/${teacher?.path}")
                                        }
                                    } else {
                                        Modifier
                                    }
                                )
                            }
                        }
                    }
                    /* End teacher name */
                    Spacer(modifier = Modifier.height(4.dp))

                    /* Lesson time */
                    Row {
                        Text(text = lesson.timeRange, style = MaterialTheme.typography.bodySmall)
                    }
                    /* End lesson time */
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp),
            ) {
                Text(
                    text = lesson.rooms.joinToString(separator = " / ") { it.name },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f)
                )
                /*SuggestionChip(
                    onClick = {},
                    label = { Text(text = lesson.location) },
                    icon = { Icon(Icons.Default.LocationOn, null)
                    })*/
                SuggestionChip(
                    onClick = {},
                    label = { Text(text = lesson.type.localized().uppercase()) })
            }
        }
    }
}

@Composable
fun LessonAnnotationsView(annotations: List<LessonAnnotation>, onClick: () -> Unit) {
    Box (Modifier.padding(start = 8.dp)) {
        annotations.sortedBy { it.type.priority }.forEachIndexed { index, lessonAnnotation ->
            lessonAnnotation.View(Modifier.offset(0.dp, (index * 8).dp).clickable(onClick=onClick))
        }
    }
}