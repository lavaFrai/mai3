package ru.lavafrai.maiapp.data.localizers

import android.content.Context
import ru.lavafrai.mai.api.models.time.Date
import ru.lavafrai.maiapp.utils.toLocalizedMonthString

fun Date.toLocalizedDayMonthString(context: Context): String {
    return "${day.toString().padStart(2, '0')} ${(month.toInt() - 1).toLocalizedMonthString(context.resources)}"
}