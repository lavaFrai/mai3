package ru.lavafrai.maiapp.widget.fragments

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.width
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.utils.toLocalizedDayOfWeekString
import ru.lavafrai.maiapp.utils.toLocalizedMonthString
import ru.lavafrai.maiapp.widget.RefreshUnloadedScheduleAction
import java.util.Calendar

@Composable
fun ScheduleWidgetHeader(context: Context) {
    val calendar = Calendar.getInstance()
    Column () {
        Box () {
            Row (
                GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalAlignment = Alignment.Vertical.CenterVertically
            ) {
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
            Row (
                GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalAlignment = Alignment.Vertical.CenterVertically
            ) {
                Image(
                    provider = ImageProvider(R.drawable.ic_refresh),
                    contentDescription = null,
                    modifier = GlanceModifier.height(22.dp).width(22.dp).clickable {
                        actionRunCallback<RefreshUnloadedScheduleAction>()
                    },
                    contentScale = ContentScale.Fit
                )

            }
        }
        Spacer(GlanceModifier.height(4.dp))
        GlanceSeparator()
    }
}