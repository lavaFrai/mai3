package ru.lavafrai.maiapp.ui.fragments.schedule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.lavafrai.maiapp.data.getSampleLessonSchedule
import ru.lavafrai.maiapp.data.models.schedule.ScheduleLesson
import ru.lavafrai.maiapp.data.models.schedule.localized
import ru.lavafrai.maiapp.ui.fragments.PairName

@Preview
@Composable
fun ScheduleLessonView(lesson: ScheduleLesson = getSampleLessonSchedule()) {
    Card(modifier = Modifier
        .padding(8.dp)
    ) {
        Column (modifier = Modifier.clickable {  }) {

            Row() {
                PairName(
                    modifier = Modifier.padding(8.dp),
                    text = lesson.getPairNumber().toString()
                )
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
                    Row {
                        Text(text = lesson.timeRange, style = MaterialTheme.typography.bodySmall)
                    }
                    Row {
                        Text(text = lesson.teacher, style = MaterialTheme.typography.bodySmall)
                    }

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
                    text = lesson.location,
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