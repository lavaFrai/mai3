package ru.lavafrai.maiapp.widget.fragments

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.unit.ColorProvider
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
fun VerticalSeparator(color: ColorProvider = WidgetColors.PRIMARY, width: Dp = 2.dp) {
    Box(modifier = GlanceModifier.fillMaxHeight().background(color).width(width).padding(8.dp)) {}
}
@Composable
fun VerticalSeparatorSized(color: ColorProvider = WidgetColors.PRIMARY, width: Dp = 2.dp, height: Dp = 2.dp) {
    Box(modifier = GlanceModifier.height(height).background(color).width(width).padding(8.dp)) {}
}
