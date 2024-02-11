package ru.lavafrai.maiapp.widget.fragments

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import ru.lavafrai.maiapp.utils.toLocalizedDayOfWeekString
import java.util.Calendar

@Composable
fun ScheduleWidgetContentList(context: Context) {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, -1)

    val dates = List(7) {
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        calendar.clone() as Calendar
    }

    LazyColumn() {
        items (dates) { date ->
            WidgetContentDay(date, context)
        }
    }
}


@SuppressLint("DefaultLocale")
@Composable
fun WidgetContentDay(date: Calendar, context: Context) {
    val daySchedule = null

    Column {
        Spacer(modifier = GlanceModifier.height(4.dp))
        WidgetText(
            text = "${date.get(Calendar.DAY_OF_WEEK).toLocalizedDayOfWeekString(context.resources).capitalize()}, ${date.get(Calendar.DAY_OF_MONTH)}"
        )


    }
}