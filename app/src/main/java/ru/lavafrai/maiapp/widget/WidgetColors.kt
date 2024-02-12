package ru.lavafrai.maiapp.widget

import androidx.compose.ui.graphics.Color
import androidx.glance.color.ColorProvider
import ru.lavafrai.maiapp.ui.theme.MaiColor

class WidgetColors {
    companion object {
        val BACKGROUND = ColorProvider(
            day = Color(0xFF222222).copy(alpha = 0.9f),
            night = Color(0xFF131313).copy(alpha = 0.9f)
        )

        val ON_BACKGROUND = ColorProvider(
            day = Color.White,
            night = Color.White
        )

        val PRIMARY = ColorProvider(
            day = MaiColor,
            night = MaiColor
        )

        val SECONDARY = ColorProvider(
            day = Color.White.copy(alpha = 0.4f),
            night = Color.White.copy(alpha = 0.4f)
        )
    }
}