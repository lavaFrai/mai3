package ru.lavafrai.maiapp.widget.fragments

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.width
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.models.SerializableDate
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiapp.data.models.schedule.ScheduleLesson
import ru.lavafrai.maiapp.utils.toLocalizedDayOfWeekString
import ru.lavafrai.maiapp.utils.toLocalizedMonthString
import java.util.Calendar

@Composable
fun ScheduleWidgetContentList(context: Context, schedule: Schedule) {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -1)

    val dates = List(7) {
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        calendar.clone() as Calendar
    }

    LazyColumn() {
        items (dates) { date ->
            WidgetContentDay(date, context, schedule)
        }
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun WidgetContentDay(date: Calendar, context: Context, schedule: Schedule) {
    val daySchedule = schedule.getScheduleOfDay(SerializableDate.of(date))

    Column {
        Spacer(modifier = GlanceModifier.height(8.dp))
        WidgetText(
            text = "${
                date.get(Calendar.DAY_OF_WEEK).toLocalizedDayOfWeekString(context.resources).capitalize()
            }, ${
                date.get(Calendar.DAY_OF_MONTH)
            } ${
                date.get(Calendar.MONTH).toLocalizedMonthString(context.resources)
            }"
        )
        Spacer(GlanceModifier.height(8.dp))

        daySchedule.lessons.forEach { lesson ->
            WidgetLessonView(lesson)
        }
        if (daySchedule.lessons.isEmpty()) {
            WidgetEmptyDayView(context)
        }
    }
}


@Composable
fun WidgetLessonView(lesson: ScheduleLesson) {
    Row {
        Spacer(GlanceModifier.width(8.dp))
        Column {
            Row {
                Column {
                    WidgetTextCompact(text = lesson.getStartTime())
                    WidgetTextCompact(text = lesson.getEndTime())
                }
                Spacer(GlanceModifier.width(4.dp))
                VerticalSeparator()
                Spacer(GlanceModifier.width(4.dp))
                Column {
                    WidgetTextCompact(text = lesson.name, maxLines = 1, )
                    WidgetTextCompact(text = lesson.location, maxLines = 1, )
                }
            }
        }
    }
    Spacer(GlanceModifier.height(8.dp))

}

@Composable
fun WidgetEmptyDayView(context: Context) {
    Row {
        Spacer(GlanceModifier.width(8.dp))
        WidgetTextCompact(text =    context.resources.getString(R.string.no_lessons_in_this_day), maxLines = 1)
    }
    Spacer(GlanceModifier.height(8.dp))
}