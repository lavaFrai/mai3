package ru.lavafrai.maiapp

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.getSettings
import ru.lavafrai.maiapp.data.models.schedule.GroupId
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import kotlin.concurrent.thread

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainView()
        }
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

                thread {
                    scheduleManager.downloadSchedule(GroupId("М4О-106Б-23"))
                }

                Text("Has schedule: ${scheduleManager.hasSchedule(GroupId("М4О-106Б-23"))}")
                Text("Settings: ${Json.encodeToString(getSettings(LocalContext.current))}")
            }
        }
    }
}
