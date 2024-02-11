package ru.lavafrai.maiapp.widget.fragments

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import ru.lavafrai.maiapp.widget.WidgetColors

@Composable
fun Separator() {
    Box(modifier = GlanceModifier.fillMaxWidth().background(WidgetColors.ON_BACKGROUND).height(0.5f.dp).padding(8.dp)) {}
}
