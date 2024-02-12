package ru.lavafrai.maiapp.ui.fragments

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalConfiguration
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
    val selected = rememberSaveable { mutableIntStateOf(0) }


    /*when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            NavigationItemsVertical(selected, onSelectionChanged)
        }
        else -> {
            NavigationItemsHorizontal(selected, onSelectionChanged)
        }
    }*/

    NavigationItemsHorizontal(selected, onSelectionChanged)
}

@Composable
fun NavigationItemsHorizontal(
    selected: MutableIntState,
    onSelectionChanged: (MainNavigationVariants) -> Unit
) {
    NavigationBar {
        Row {
            NavigationBarItem(
                selected = selected.intValue == 0,
                label = { Text(stringResource(id = R.string.schedule)) },
                onClick = {
                    selected.intValue = 0
                    onSelectionChanged(MainNavigationVariants.SCHEDULE)
                },
                icon = { Icon(Icons.Default.DateRange, null) },
                alwaysShowLabel = false
            )
            NavigationBarItem(
                selected = selected.intValue == 1,
                label = { Text(stringResource(id = R.string.settings)) },
                onClick = {
                    selected.intValue = 1
                    onSelectionChanged(MainNavigationVariants.SETTINGS)
                },
                icon = { Icon(Icons.Default.Settings, null) },
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
fun NavigationItemsVertical(
    selected: MutableIntState,
    onSelectionChanged: (MainNavigationVariants) -> Unit
) {
    NavigationRail {

        Column {
            NavigationRailItem(
                selected = selected.intValue == 0,
                label = { Text(stringResource(id = R.string.schedule)) },
                onClick = {
                    selected.intValue = 0
                    onSelectionChanged(MainNavigationVariants.SCHEDULE)
                },
                icon = { Icon(Icons.Default.DateRange, null) },
                alwaysShowLabel = false
            )
            NavigationRailItem(
                selected = selected.intValue == 1,
                label = { Text(stringResource(id = R.string.settings)) },
                onClick = {
                    selected.intValue = 1
                    onSelectionChanged(MainNavigationVariants.SETTINGS)
                },
                icon = { Icon(Icons.Default.Settings, null) },
                alwaysShowLabel = false
            )
        }
    }
}