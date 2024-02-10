package ru.lavafrai.maiapp.data.models.group

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.lavafrai.maiapp.R

enum class GroupType {
    BACHELOR,
    MAGISTRACY,
    POSTGRADUATE, // Аспирантура

    SPECIAL,
    BASE_HIGH,
    SPECIAL_HIGH,
}


@Composable
fun GroupType?.localized(context: Context): String {
    if (this == null) return "Unknown"
    return stringResource(when (this) {
        GroupType.BACHELOR -> R.string.bachelor
        GroupType.MAGISTRACY -> R.string.magistracy
        GroupType.POSTGRADUATE -> R.string.postgraduate
        GroupType.SPECIAL -> R.string.special
        GroupType.BASE_HIGH -> R.string.base_high
        GroupType.SPECIAL_HIGH -> R.string.special_high
        else -> R.string.unknown
    })
}
