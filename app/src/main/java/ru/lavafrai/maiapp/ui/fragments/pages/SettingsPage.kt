package ru.lavafrai.maiapp.ui.fragments.pages

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import io.appmetrica.analytics.AppMetrica
import ru.lavafrai.maiapp.Mai3
import ru.lavafrai.maiapp.MainActivity
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.PROJECT_GITHUB_URL
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.models.group.GroupId
import ru.lavafrai.maiapp.data.models.group.GroupNameAnalyzer
import ru.lavafrai.maiapp.data.models.group.localized
import ru.lavafrai.maiapp.ui.fragments.DangerButton
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import kotlin.system.exitProcess


@Composable
fun SettingsPage() {
    val context = LocalContext.current
    // val activity = LocalContext.current as Activity

    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        SettingsHeader()

        SettingsUserCard(Settings.getCurrentGroup()!!)

        SettingsGroupControls()
        SettingsThemeControls()
        SettingsUIDCard()
        SettingsSourcesCard()
    }

    /*
    Column (
        Modifier.padding(8.dp)
    ) {

        Text(text = "Settings not implemented yet")
        Text(text = "MAI 3 app by. lava_frai")
        Text(text = "Sources: https://github.com/lavaFrai/mai3")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            activity.startActivity(Intent(
                context,
                GroupSelectActivity::class.java
            ))
            activity.finish()
        }) {
            Text(text = "Change group")
        }
    }*/
}


@Composable
fun SettingsSourcesCard() {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
    ) {
        Box(Modifier.clickable {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(PROJECT_GITHUB_URL))
            startActivity(context, browserIntent, null)
        }) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                TextH3(
                    text = stringResource(id = R.string.open_source),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )

                Text(text = stringResource(id = R.string.open_source_description))
            }
        }
    }
}


@Composable
fun SettingsUIDCard() {
    val uid = AppMetrica.getDeviceId(LocalContext.current) ?: "<Error>"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 16.dp)
            .padding(top = 0.dp)
    ) {
        Box(Modifier.clickable {
            Mai3.copyString(uid)
        }) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                TextH3(
                    text = stringResource(id = R.string.uid),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )

                Text(
                    text = uid,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.uid_description))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.click_to_copy),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SettingsThemeControls() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp)
            .padding(top = 32.dp)
    ) {
        Box(Modifier.clickable { }) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                TextH3(
                    text = stringResource(id = R.string.app_theme),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )


                var selected by remember { mutableStateOf(0) }

                SingleChoiceSegmentedButtonRow (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = 0, count = 3),
                        onClick = { Settings.setIsDarkTheme(false) ; MainActivity.manualRecompose() ; selected = 0 },
                        selected = selected == 0
                    ) {
                        Text(stringResource(id = R.string.day_theme))
                    }

                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = 1, count = 3),
                        onClick = { Settings.setIsDarkTheme(null) ; MainActivity.manualRecompose() ; selected = 1 },
                        selected = selected == 1
                    ) {
                        Text(stringResource(id = R.string.default_theme))
                    }

                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = 2, count = 3),
                        onClick = { Settings.setIsDarkTheme(true) ; MainActivity.manualRecompose() ; selected = 2 },
                        selected = selected == 2
                    ) {
                        Text(stringResource(id = R.string.night_theme))
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsGroupControls() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        Button(onClick = {
            ScheduleManager(context).deleteSchedule(Settings.getCurrentGroup())
        }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(id = R.string.redownload_schedule))
        }
        DangerButton(
            onClick = {
                Mai3.wipeData()
                exitProcess(0)
            }, modifier = Modifier.fillMaxWidth(), dialogText = stringResource(
                id = R.string.data_clear_confirmation
            )
        ) {
            Text(stringResource(id = R.string.wipe_data))
        }
    }
}


@Preview
@Composable
fun SettingsUserCard(groupId: GroupId = GroupId("М14О-102БВ-23")) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 32.dp)
    ) {
        Box(Modifier.clickable { }) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                TextH3(
                    text = groupId.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )

                val groupInfo = GroupNameAnalyzer(groupId.name)
                UserInfoLine(text = stringResource(R.string.faculty_num) + groupInfo.faculty.toString())
                UserInfoLine(
                    text = groupInfo.type.localized(context = LocalContext.current).capitalize()
                )
                UserInfoLine(text = stringResource(id = R.string.course_num) + " " + groupInfo.course.toString())

                Text(
                    text = stringResource(id = R.string.tour_group),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(0.dp, 6.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}


@Composable
fun SettingsHeader() {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp, 16.dp)
                .fillMaxWidth(),
        ) {
            TextH3(text = stringResource(id = R.string.settings))
        }

        Box(
            modifier = Modifier
                .height(0.5.dp)
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun UserInfoLine(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(0.dp, 6.dp)
    )
}