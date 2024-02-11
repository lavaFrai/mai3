package ru.lavafrai.maiapp.widget.fragments

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextDefaults
import androidx.glance.text.TextStyle
import ru.lavafrai.maiapp.widget.WidgetColors

@Composable
fun WidgetText(
    text: String,
    modifier: GlanceModifier = GlanceModifier,
    style: TextStyle = TextDefaults.defaultTextStyle,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        modifier = modifier,
        style = style.copy(color = WidgetColors.ON_BACKGROUND, fontSize = 16.sp),
        maxLines = maxLines
    )
}

@Composable
fun WidgetTextHeader(
    text: String,
    modifier: GlanceModifier = GlanceModifier,
    style: TextStyle = TextDefaults.defaultTextStyle,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        modifier = modifier,
        style = style.copy(color = WidgetColors.ON_BACKGROUND, fontSize = 17.sp, fontWeight = FontWeight.Bold),
        maxLines = maxLines
    )
}