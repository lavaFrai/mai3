package ru.lavafrai.maiapp.ui.fragments.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.mai.api.models.time.Date
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.models.LessonAnnotation
import ru.lavafrai.maiapp.data.models.LessonAnnotationType
import ru.lavafrai.maiapp.data.models.getAnnotationOrNull
import ru.lavafrai.maiapp.data.models.isAnnotatedBy
import ru.lavafrai.maiapp.data.models.localized
import ru.lavafrai.maiapp.data.models.setAnnotationData
import ru.lavafrai.maiapp.data.models.toggle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonAnnotationDialog(
    state: MutableState<Boolean> = mutableStateOf(true),
    annotations: List<LessonAnnotation> = listOf(),
    day: Date,
    lesson: Lesson,
    onSave: (List<LessonAnnotation>) -> Unit = {},
) {
    val title = lesson.name

    if (state.value) Dialog(onDismissRequest = { state.value = false }) {
        Card(
            Modifier.clip(RoundedCornerShape(16.dp))
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.annotate_as),
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(8.dp))

                AnnotationToggle(annotations, lesson, LessonAnnotation.ControlWork, onSave)
                AnnotationToggle(annotations, lesson, LessonAnnotation.HomeWork, onSave)

                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Button(onClick = { state.value = false }) {
                        Text(text = stringResource(id = R.string.save))
                    }
                }
            }
        }
    }
}

@Composable
fun AnnotationToggle(
    annotations: List<LessonAnnotation>,
    lesson: Lesson,
    type: LessonAnnotationType,
    onSave: (List<LessonAnnotation>) -> Unit
) {

    Column (Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = annotations.isAnnotatedBy(lesson, type),
                onCheckedChange = { onSave(annotations.toggle(lesson, type)) }
            )
            Text(text = type.localized())
        }

        AnimatedVisibility(
            type.hasUserData && annotations.isAnnotatedBy(lesson, type),
            enter = expandVertically(animationSpec = keyframes { this.durationMillis = 100 }),
            exit = shrinkVertically(animationSpec = keyframes { this.durationMillis = 100 }),
        ) {
            var userDataText by rememberSaveable { mutableStateOf(annotations.getAnnotationOrNull(lesson, type)!!.data!!) }
            OutlinedTextField(
                value = userDataText,
                onValueChange = { userDataText = it ; onSave(annotations.setAnnotationData(lesson, type, userDataText)) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
