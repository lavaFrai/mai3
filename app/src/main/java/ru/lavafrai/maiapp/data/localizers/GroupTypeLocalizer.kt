package ru.lavafrai.maiapp.data.localizers

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.lavafrai.mai.api.models.group.GroupType


@Composable
fun GroupType?.localized(context: Context): String {
    if (this == null) return "Unknown"
    return stringResource(when (this) {
        GroupType.BACHELOR -> ru.lavafrai.maiapp.R.string.bachelor
        GroupType.MAGISTRACY -> ru.lavafrai.maiapp.R.string.magistracy
        GroupType.POSTGRADUATE -> ru.lavafrai.maiapp.R.string.postgraduate
        GroupType.SPECIAL -> ru.lavafrai.maiapp.R.string.special
        GroupType.BASE_HIGH -> ru.lavafrai.maiapp.R.string.base_high
        GroupType.SPECIAL_HIGH -> ru.lavafrai.maiapp.R.string.special_high
        else -> ru.lavafrai.maiapp.R.string.unknown
    })
}
