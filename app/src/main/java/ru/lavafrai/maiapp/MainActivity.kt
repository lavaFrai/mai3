package ru.lavafrai.maiapp

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.getSettings
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainView()
        }
    }


    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
    @Composable
    fun MainView() {
        MAI30Theme {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                topBar = {},
                bottomBar = {},
                floatingActionButton = {},
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding),

                    ) {
                    val scheduleManager = ScheduleManager(LocalContext.current)
                    val (hasScheduleDownloaded, setHasActualScheduleDownloaded) = rememberSaveable { mutableStateOf(scheduleManager.hasActualScheduleDownloaded()) }
                    val (loadedText, setLoadedText) = rememberSaveable { mutableStateOf("loading...") }

                    if (!scheduleManager.hasActualSchedule()) {
                        val activity = LocalContext.current as Activity
                        activity.startActivity(Intent(
                            this@MainActivity,
                            GroupSelectActivity::class.java
                        ))
                        finish()
                    }

                    thread {
                        setLoadedText(
                            scheduleManager.getActualSchedule().toString()
                        )
                        setHasActualScheduleDownloaded(
                            scheduleManager.hasActualScheduleDownloaded()
                        )
                    }

                    Text("Has schedule: $hasScheduleDownloaded")
                    Text("Group: ${getSettings(LocalContext.current).currentGroup}")
                    Text("Loaded schedule: $loadedText")
                }
            }
        }
    }
}
