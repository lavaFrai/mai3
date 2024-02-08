package ru.lavafrai.maiapp.ui.fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.lavafrai.maiapp.R


enum class MainNavigationVariants {
    SCHEDULE,
    SETTINGS
}


@Preview
@Composable
fun MainNavigationBar(onSelectionChanged: (MainNavigationVariants) -> Unit = {}) {
    var selected by rememberSaveable { mutableIntStateOf(0) }

    NavigationBar {
        Row {
            NavigationBarItem(
                selected = selected == 0,
                label = { Text(stringResource(id = R.string.schedule)) },
                onClick = {
                    selected = 0
                    onSelectionChanged(MainNavigationVariants.SCHEDULE)
                },
                icon = { Icon(Icons.Default.DateRange, null) },
                alwaysShowLabel = false
            )
            NavigationBarItem(
                selected = selected == 1,
                label = { Text(stringResource(id = R.string.settings)) },
                onClick = {
                    selected = 1
                    onSelectionChanged(MainNavigationVariants.SETTINGS)
                },
                icon = { Icon(Icons.Default.Settings, null) },
                alwaysShowLabel = false
            )
        }
    }
}