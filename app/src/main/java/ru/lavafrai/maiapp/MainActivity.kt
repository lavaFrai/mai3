package ru.lavafrai.maiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.models.group.GroupId
import ru.lavafrai.maiapp.data.models.schedule.Schedule
import ru.lavafrai.maiapp.systems.AppSystemName
import ru.lavafrai.maiapp.systems.permissions.PermissionsSystem
import ru.lavafrai.maiapp.ui.fragments.MainNavigationBar
import ru.lavafrai.maiapp.ui.fragments.MainNavigationVariants
import ru.lavafrai.maiapp.ui.pages.SchedulePage
import ru.lavafrai.maiapp.ui.pages.SettingsPage
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import ru.lavafrai.maiapp.widget.ScheduleWidget
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    init {
        // MainActivity.instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        updateWidget = {
            val coroutineScope = MainScope()
            coroutineScope.launch {
                ScheduleWidget().updateAll(this@MainActivity)
            }
        }
        updateWidget()

        var schedule: Schedule? = null
        val scheduleLoaded = mutableStateOf<Boolean?>(null)
        thread {
            schedule = ScheduleManager(this).getActualSchedule()
            scheduleLoaded.value = schedule != null
        }

        setContent {
            val isDarkTheme = remember { mutableStateOf<Boolean?>(Settings.getIsDarkTheme()) }
            val isDynamicColors = rememberSaveable { mutableStateOf(Settings.isDynamicColors()) }
            val currentGroup = remember { mutableStateOf(Settings.getCurrentGroup()) }

            manualRecompose = {
                isDarkTheme.value = true
                isDarkTheme.value = Settings.getIsDarkTheme()
                isDynamicColors.value = Settings.isDynamicColors()
                currentGroup.value = Settings.getCurrentGroup()!!
                scheduleLoaded.value = null
                thread {
                    schedule = ScheduleManager(this).getActualSchedule()
                    scheduleLoaded.value = schedule != null
                }
            }

            MainView(
                isDarkTheme.value ?: isSystemInDarkTheme(),
                isDynamicColors.value,
                currentGroup,
                schedule,
                scheduleLoaded.value,
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
        currentGroup: MutableState<GroupId?>,
        schedule: Schedule?,
        scheduleLoaded: Boolean?,
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
                        MainNavigationVariants.SCHEDULE -> SchedulePage(currentGroup.value, schedule, scheduleLoaded)
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
