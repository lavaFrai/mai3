package ru.lavafrai.maiapp.widget

import androidx.compose.ui.graphics.Color
import androidx.glance.color.ColorProvider
import ru.lavafrai.maiapp.ui.theme.MaiColor

class WidgetColors {
    companion object {
        val BACKGROUND = ColorProvider(
            day = Color.Black.copy(alpha = 0.6f),
            night = Color.Gray.copy(alpha = 0.6f)
        )

        val ON_BACKGROUND = ColorProvider(
            day = Color.White,
            night = Color.White
        )

        val PRIMARY = ColorProvider(
            day = MaiColor,
            night = MaiColor
        )
    }
}