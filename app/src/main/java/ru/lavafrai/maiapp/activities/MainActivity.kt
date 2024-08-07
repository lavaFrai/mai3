package ru.lavafrai.maiapp.activities

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
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
import kotlinx.serialization.json.Json
import ru.lavafrai.exler.mai.Exler
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.schedule.Schedule
import ru.lavafrai.mai.api.models.schedule.ScheduleDay
import ru.lavafrai.maiapp.Mai3
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.activities.pages.ExamsPage
import ru.lavafrai.maiapp.activities.pages.InfoPage
import ru.lavafrai.maiapp.activities.pages.SchedulePage
import ru.lavafrai.maiapp.activities.pages.SettingsPage
import ru.lavafrai.maiapp.activities.pages.account.OfficialAccountPage
import ru.lavafrai.maiapp.api.LocalApi
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.systems.AppSystemName
import ru.lavafrai.maiapp.systems.permissions.PermissionsSystem
import ru.lavafrai.maiapp.ui.fragments.MainNavigationBar
import ru.lavafrai.maiapp.ui.fragments.MainNavigationVariants
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import ru.lavafrai.maiapp.utils.LockScreenOrientation
import ru.lavafrai.maiapp.utils.encodeToFile
import ru.lavafrai.maiapp.widget.ScheduleWidget
import java.io.File
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

        if (Settings.isApplicantMode()) {
            openApplicantMode()
            return
        }

        var schedule: MutableState<Schedule?> = mutableStateOf(null as Schedule?)
        val scheduleLoaded = mutableStateOf<Boolean?>(null)
        var weekSchedule: MutableState<List<ScheduleDay>?> = mutableStateOf(listOf<ScheduleDay>())

        loadSchedule {
            schedule.value = it
            scheduleLoaded.value = schedule.value != null
            weekSchedule.value = schedule.value?.getCurrentWeekSchedule()

            updateScheduleAsync(schedule)
        }

        setSchedule = {
            newSchedule -> schedule.value = newSchedule
        }

        setContent {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            val isDarkTheme = remember { mutableStateOf<Boolean?>(Settings.getIsDarkTheme()) }
            val isDynamicColors = rememberSaveable { mutableStateOf(Settings.isDynamicColors()) }
            val currentGroup = remember { mutableStateOf(Settings.getCurrentGroup()) }

            manualRecompose = {
                isDarkTheme.value = true
                isDarkTheme.value = Settings.getIsDarkTheme()
                isDynamicColors.value = Settings.isDynamicColors()
                currentGroup.value = Settings.getCurrentGroup()!!
                scheduleLoaded.value = null
                loadSchedule {
                    schedule.value = it
                    scheduleLoaded.value = schedule.value != null
                    weekSchedule.value = schedule.value?.getCurrentWeekSchedule()
                }
            }

            MainView(
                isDarkTheme.value ?: isSystemInDarkTheme(),
                isDynamicColors.value,
                currentGroup,
                schedule.value,
                scheduleLoaded.value,
                weekSchedule,
            )
        }
    }


    override fun onDestroy() {
        updateWidget()
        super.onDestroy()
    }

    private fun loadSchedule(after: (Schedule?) -> Unit) {
        thread {
            try {
                val schedule = ScheduleManager(this).getActualSchedule()
                after(schedule)
            } catch (e: Exception) {
                e.printStackTrace()
                after(null)
            }
        }
    }

    private fun openApplicantMode() {
        val intent = Intent(this, ApplicantActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun updateScheduleAsync(schedule: MutableState<Schedule?>, after: (Schedule) -> Unit = {}) {
        // Toast.makeText(this, "updating schedule", Toast.LENGTH_SHORT).show()
        val group = Settings.getCurrentGroup() ?: return

        thread {
            Looper.prepare()
            val newSchedule = LocalApi.getGroupScheduleOrNull(group)
            schedule.value = newSchedule ?: schedule.value
            if (newSchedule == null) { Toast.makeText(this, getString(R.string.schedule_update_failed), Toast.LENGTH_SHORT).show() }
            else {
                Log.i("APP", "Schedule updated")
                val scheduleFile = File(getExternalFilesDir("schedule"), group.name)
                Json.encodeToFile(schedule.value, scheduleFile)
                after(schedule.value!!)
            }
        }

    }

    @Composable
    fun MainView(
        isDarkTheme: Boolean,
        isDynamicColors: Boolean,
        currentGroup: MutableState<Group?>,
        schedule: Schedule?,
        scheduleLoaded: Boolean?,
        weekSchedule: MutableState<List<ScheduleDay>?>
    ) {
        val permissionSystem = Mai3.getSystem(AppSystemName.PERMISSIONS) as PermissionsSystem
        permissionSystem.requestRequired(this)
        val exler = Exler

        var selectedPage by rememberSaveable { mutableStateOf(MainNavigationVariants.SCHEDULE) }
        //var selectedWeek by rememberSaveable {  }

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
                        MainNavigationVariants.SCHEDULE -> SchedulePage(currentGroup.value, schedule, scheduleLoaded, weekSchedule, exler, )
                        MainNavigationVariants.EXAMS -> ExamsPage(currentGroup.value, schedule, scheduleLoaded, weekSchedule, exler, )
                        MainNavigationVariants.SETTINGS -> SettingsPage(currentGroup.value!!)
                        MainNavigationVariants.INFO -> InfoPage()
                        MainNavigationVariants.ACCOUNT -> OfficialAccountPage()
                        else -> {}
                    }
                }
            }
        }
    }

    companion object {
        // lateinit var instance: MainActivity

        var manualRecompose: () -> Unit = {}
        var setSchedule: (Schedule) -> Unit = {}

        lateinit var updateWidget: () -> Unit
    }
}
