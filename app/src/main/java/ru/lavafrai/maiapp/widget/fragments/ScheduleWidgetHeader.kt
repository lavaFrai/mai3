package ru.lavafrai.maiapp.widget.fragments

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.width
import ru.lavafrai.maiapp.utils.toLocalizedDayOfWeekString
import ru.lavafrai.maiapp.utils.toLocalizedMonthString
import java.util.Calendar

@Composable
fun ScheduleWidgetHeader(context: Context) {
    val calendar = Calendar.getInstance()
    Column {
        Row {
            WidgetTextHeader(text = calendar.get(Calendar.DAY_OF_MONTH).toString())
            Spacer(GlanceModifier.width(4.dp))
            WidgetTextHeader(
                text = calendar.get(Calendar.MONTH).toLocalizedMonthString(context.resources)
            )
            Spacer(GlanceModifier.width(4.dp))
            WidgetTextHeader(
                text = calendar.get(Calendar.DAY_OF_WEEK)
                    .toLocalizedDayOfWeekString(context.resources)
            )
        }
        Spacer(GlanceModifier.height(4.dp))
        Separator()
    }
}