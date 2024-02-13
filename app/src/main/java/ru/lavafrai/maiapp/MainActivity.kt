package ru.lavafrai.maiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.glance.appwidget.updateAll
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.models.group.GroupId
import ru.lavafrai.maiapp.systems.AppSystemName
import ru.lavafrai.maiapp.systems.permissions.PermissionsSystem
import ru.lavafrai.maiapp.ui.fragments.MainNavigationBar
import ru.lavafrai.maiapp.ui.fragments.MainNavigationVariants
import ru.lavafrai.maiapp.ui.pages.SchedulePage
import ru.lavafrai.maiapp.ui.pages.SettingsPage
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import ru.lavafrai.maiapp.widget.ScheduleWidget

class MainActivity : ComponentActivity() {
    init {
        // MainActivity.instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateWidget = {
            val coroutineScope = MainScope()
            coroutineScope.launch {
                ScheduleWidget().updateAll(this@MainActivity)
            }
        }
        updateWidget()

        setContent {
            val isDarkTheme = remember { mutableStateOf<Boolean?>(Settings.getIsDarkTheme()) }
            val isDynamicColors = rememberSaveable { mutableStateOf(Settings.isDynamicColors()) }
            val currentGroup = remember { mutableStateOf(Settings.getCurrentGroup()) }

            manualRecompose = {
                isDarkTheme.value = true
                isDarkTheme.value = Settings.getIsDarkTheme()
                isDynamicColors.value = Settings.isDynamicColors()
                currentGroup.value = Settings.getCurrentGroup()!!
            }

            MainView(
                isDarkTheme.value ?: isSystemInDarkTheme(),
                isDynamicColors.value,
                currentGroup,
            )
        }
    }


    override fun onDestroy() {
        updateWidget()
        super.onDestroy()
    }

    @Composable
    fun MainView(
        isDarkTheme: Boolean,
        isDynamicColors: Boolean,
        currentGroup: MutableState<GroupId?>
    ) {
        val permissionSystem = Mai3.getSystem(AppSystemName.PERMISSIONS) as PermissionsSystem
        permissionSystem.requestRequired(this)

        var selectedPage by rememberSaveable { mutableStateOf(MainNavigationVariants.SCHEDULE) }

        MAI30Theme (
            darkTheme = isDarkTheme,
            dynamicColor = isDynamicColors,
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                topBar = {},
                bottomBar = { MainNavigationBar { selectedPage = it }
                },
                floatingActionButton = {},
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding),

                ) {
                    when (selectedPage) {
                        MainNavigationVariants.SCHEDULE -> SchedulePage(currentGroup.value)
                        MainNavigationVariants.SETTINGS -> SettingsPage(currentGroup.value!!)
                        else -> {}
                    }
                }
            }
        }
    }

    companion object {
        // lateinit var instance: MainActivity

        var manualRecompose: () -> Unit = {}

        lateinit var updateWidget: () -> Unit
    }
}
