package ru.lavafrai.maiapp.widget.fragments

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.width
import androidx.glance.unit.ColorProvider
import ru.lavafrai.mai.api.models.schedule.Lesson
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.models.time.Date
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.localizers.localizedShortcut
import ru.lavafrai.maiapp.data.models.LessonAnnotation
import ru.lavafrai.maiapp.data.models.color
import ru.lavafrai.maiapp.utils.toLocalizedDayOfWeekString
import ru.lavafrai.maiapp.utils.toLocalizedMonthString
import ru.lavafrai.maiapp.widget.WidgetColors
import java.util.Calendar

@Composable
fun ScheduleWidgetContentList(context: Context, schedule: Schedule, annotations: List<LessonAnnotation>) {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -1)

    val dates = List(7) {
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        calendar.clone() as Calendar
    }

    LazyColumn () {
        items(dates) { date ->
            WidgetContentDay(date, context, schedule, annotations)
        }
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun WidgetContentDay(date: Calendar, context: Context, schedule: Schedule, annotations: List<LessonAnnotation>) {
    val daySchedule = schedule.getScheduleOfDay(Date.of(date))

    Column () {
        Spacer(modifier = GlanceModifier.height(8.dp))
        WidgetText(
            text = "${
                date.get(Calendar.DAY_OF_WEEK).toLocalizedDayOfWeekString(context.resources)
                    .capitalize()
            }, ${
                date.get(Calendar.DAY_OF_MONTH)
            } ${
                date.get(Calendar.MONTH).toLocalizedMonthString(context.resources)
            }"
        )
        Spacer(GlanceModifier.height(8.dp))

        Column {
            daySchedule.lessons.sortedBy { it.getPairNumber() }.forEach { lesson ->
                WidgetLessonView(lesson, context, annotations.filter { it.lessonUid == lesson.getUid() })
            }
            if (daySchedule.lessons.isEmpty()) {
                WidgetEmptyDayView(context)
            }
        }
    }
}

@Composable
fun WidgetLessonView(lesson: Lesson, context: Context, lessonAnnotations: List<LessonAnnotation>) {
    Column {
        Spacer(GlanceModifier.width(8.dp))
        Row {
            Column {
                WidgetTextCompact(text = lesson.timeRange.split(" – ")[0])
                WidgetTextCompact(text = lesson.timeRange.split(" – ")[1])
            }
            Spacer(GlanceModifier.width(4.dp))
            VerticalSeparator(color = lessonAnnotations.maxByOrNull { it.type.priority }?.color() ?: WidgetColors.PRIMARY.getColor(context))
            Spacer(GlanceModifier.width(4.dp))
            Column {
                WidgetTextCompact(text = lesson.name, maxLines = 1)
                Row (
                    verticalAlignment = Alignment.Vertical.CenterVertically
                ) {
                    WidgetTextCompact(text = lesson.type.localizedShortcut(context))
                    Spacer(GlanceModifier.width(4.dp))
                    VerticalSeparatorSized(color = ColorProvider(Color.Gray.copy(alpha = 0.6f)), width = 1.2f.dp, height = 14.dp)
                    Spacer(GlanceModifier.width(4.dp))
                    WidgetTextCompact(text = lesson.rooms.joinToString(" / ") {it.name})
                }
            }
        }
        Spacer(GlanceModifier.height(8.dp))
    }
}

@Composable
fun WidgetEmptyDayView(context: Context) {
    Row {
        Spacer(GlanceModifier.width(8.dp))
        WidgetTextCompact(
            text = context.resources.getString(R.string.no_lessons_in_this_day),
            maxLines = 1
        )
    }
    Spacer(GlanceModifier.height(8.dp))
}
