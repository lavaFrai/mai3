package ru.lavafrai.maiapp.widget.fragments

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import ru.lavafrai.maiapp.widget.WidgetColors

@Composable
fun Separator() {
    Box(modifier = GlanceModifier.fillMaxWidth().background(WidgetColors.ON_BACKGROUND).height(0.5f.dp).padding(8.dp)) {}
}


@Composable
fun SecondarySeparator() {
    Box(modifier = GlanceModifier.fillMaxWidth().background(WidgetColors.SECONDARY).height(1f.dp).padding(8.dp)) {}
}


@Composable
fun VerticalSeparator() {
    Box(modifier = GlanceModifier.fillMaxHeight().background(WidgetColors.PRIMARY).width(2f.dp).padding(8.dp)) {}
}
