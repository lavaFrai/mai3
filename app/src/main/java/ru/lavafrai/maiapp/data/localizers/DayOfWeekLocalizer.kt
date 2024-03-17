package ru.lavafrai.maiapp.data.localizers

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.lavafrai.mai.api.models.time.DayOfWeek
import ru.lavafrai.maiapp.R


@Composable
fun DayOfWeek.localized(): String {
    return when(this) {
        DayOfWeek.MONDAY -> stringResource(id = R.string.monday)
        DayOfWeek.TUESDAY -> stringResource(id = R.string.tuesday)
        DayOfWeek.WEDNESDAY -> stringResource(id = R.string.wednesday)
        DayOfWeek.THURSDAY -> stringResource(id = R.string.thursday)
        DayOfWeek.FRIDAY -> stringResource(id = R.string.friday)
        DayOfWeek.SATURDAY -> stringResource(id = R.string.saturday)
        DayOfWeek.SUNDAY -> stringResource(id = R.string.sunday)
    }
}