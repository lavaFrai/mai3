package ru.lavafrai.maiapp.activities.pages

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.core.content.ContextCompat.startActivity
import androidx.glance.appwidget.GlanceAppWidgetManager
import io.appmetrica.analytics.AppMetrica
import kotlinx.coroutines.launch
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.maiapp.BuildConfig
import ru.lavafrai.maiapp.Mai3
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.activities.GroupSelectActivity
import ru.lavafrai.maiapp.activities.MainActivity
import ru.lavafrai.maiapp.data.PROJECT_DONATION_URL
import ru.lavafrai.maiapp.data.PROJECT_GITHUB_URL
import ru.lavafrai.maiapp.data.PROJECT_TELEGRAM_URL
import ru.lavafrai.maiapp.data.ScheduleManager
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.localizers.localized
import ru.lavafrai.maiapp.ui.fragments.DangerButton
import ru.lavafrai.maiapp.ui.fragments.PageTitle
import ru.lavafrai.maiapp.ui.fragments.properties.PropertyBoolean
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.utils.analyzeName
import ru.lavafrai.maiapp.utils.readableFileSize
import ru.lavafrai.maiapp.widget.ScheduleWidgetReceiver
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.system.exitProcess


@Composable
fun SettingsPage(currentGroup: Group) {
    val context = LocalContext.current
    // val activity = LocalContext.current as Activity
    val scheduleManager = ScheduleManager(context)

    PageTitle(title = stringResource(id = R.string.settings), padded = false, scrollable = false) {
        Column (Modifier.verticalScroll(rememberScrollState())) {
            SettingsGroupCard(currentGroup, scheduleManager)


            SettingsThemeControls()
            Spacer(modifier = Modifier.height(16.dp))
            SettingsWidget()
            SettingsSwitchesPage()
            // SettingsUIDCard()
            SettingsTelegram()
            SettingsSourcesCard()
            SettingsDonation()

            DangerButton(
                onClick = {
                    Mai3.wipeData()
                    exitProcess(0)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), dialogText = stringResource(
                    id = R.string.data_clear_confirmation
                )
            ) {
                Text(stringResource(id = R.string.wipe_data))
            }

            Text(
                text = "MAI app by. lava_frai\nBuild: ${BuildConfig.BUILD_TYPE}@${BuildConfig.VERSION_NAME}",
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 0.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
            )
        }

    }
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



@Preview
@Composable
fun SettingsSwitchesPage() {
    Card(
        Modifier
            .padding(16.dp, 0.dp)
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            Modifier.padding(16.dp),
        ) {
            TextH3(text = stringResource(id = R.string.properties))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PropertyBoolean(
                    stringResource(id = R.string.property_dynamic_colors),
                    Settings.isDynamicColors()
                ) { Settings.setDynamicColors(it); MainActivity.manualRecompose() }
            } else {
                PropertyBoolean(
                    stringResource(id = R.string.property_dynamic_colors),
                    isSet = false,
                    enabled = false,
                )
            }

            if (BuildConfig.BUILD_TYPE == "debug") {
                var isUseServerCache by remember { mutableStateOf(Settings.isUseServerCache()) }
                PropertyBoolean(
                    stringResource(id = R.string.use_server_cache),
                    isSet = isUseServerCache,
                ) { Settings.setUseServerCache(it); isUseServerCache = Settings.isUseServerCache() }
            }
        }
    }
}


@Preview
@Composable
fun SettingsWidget() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                TextH3(
                    text = stringResource(id = R.string.widget),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            Text(text = stringResource(id = R.string.widget_settings_description))
            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                scope.launch {
                    GlanceAppWidgetManager(context).requestPinGlanceAppWidget(
                        ScheduleWidgetReceiver::class.java, successCallback = null
                    )
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.create_widget))
            }
        }
    }
}

@Preview
@Composable
fun SettingsTelegram() {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Box(Modifier.clickable {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(PROJECT_TELEGRAM_URL))
            startActivity(context, browserIntent, null)
        }) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row {
                    Icon(
                        painterResource(R.drawable.ic_telegram),
                        null,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                    TextH3(
                        text = stringResource(id = R.string.community),
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(bottom = 12.dp, start = 8.dp)
                    )
                }

                Text(text = stringResource(id = R.string.community_description))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = PROJECT_TELEGRAM_URL,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f),
                )
                TextButton(onClick = {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(PROJECT_TELEGRAM_URL))
                    startActivity(context, browserIntent, null)
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(id = R.string.open_telegram))
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsDonation() {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Box(Modifier.clickable { }) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row {
                    TextH3(
                        text = stringResource(id = R.string.donation),
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }

                Text(text = stringResource(id = R.string.donation_description))
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(PROJECT_DONATION_URL))
                    startActivity(context, browserIntent, null)
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(id = R.string.open_donation))
                }
            }
        }
    }
}


@Preview
@Composable
fun SettingsSourcesCard() {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Box(Modifier.clickable {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(PROJECT_GITHUB_URL))
            startActivity(context, browserIntent, null)
        }) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row {
                    Icon(
                        painterResource(R.drawable.ic_github),
                        null,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                    TextH3(
                        text = stringResource(id = R.string.open_source),
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(bottom = 12.dp, start = 8.dp)
                    )
                }

                Text(text = stringResource(id = R.string.open_source_description))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = PROJECT_GITHUB_URL,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f),
                )

                TextButton(onClick = {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(PROJECT_GITHUB_URL))
                    startActivity(context, browserIntent, null)
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(id = R.string.open_github))
                }
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
                modifier = Modifier.padding(16.dp)
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
                modifier = Modifier.padding(16.dp)
            ) {
                TextH3(
                    text = stringResource(id = R.string.app_theme),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )


                var selected by remember {
                    mutableIntStateOf(
                        when (Settings.getIsDarkTheme()) {
                            true -> 2
                            false -> 0
                            else -> 1
                        }
                    )
                }

                SingleChoiceSegmentedButtonRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = 0, count = 3), onClick = {
                            Settings.setIsDarkTheme(false); MainActivity.manualRecompose(); selected =
                            0
                        }, selected = selected == 0
                    ) {
                        Text(stringResource(id = R.string.day_theme))
                    }

                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = 1, count = 3), onClick = {
                            Settings.setIsDarkTheme(null); MainActivity.manualRecompose(); selected =
                            1
                        }, selected = selected == 1
                    ) {
                        Text(stringResource(id = R.string.default_theme))
                    }

                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(index = 2, count = 3), onClick = {
                            Settings.setIsDarkTheme(true); MainActivity.manualRecompose(); selected =
                            2
                        }, selected = selected == 2
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


    }
}


@SuppressLint("SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsGroupCard(group: Group = Group("М14О-102БВ-23"), scheduleManager: ScheduleManager) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 32.dp)
    ) {
        Box(Modifier.clickable { }) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                /*TextH3(
                    text = groupId.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )*/

                GroupDropdownList(scheduleManager, group)

                val created = scheduleManager.getActualScheduleOrNull()?.created
                val groupInfo = group.analyzeName()
                UserInfoLine(text = stringResource(R.string.faculty_num) + groupInfo.faculty.toString())
                UserInfoLine(
                    text = groupInfo.type.localized(context = LocalContext.current).capitalize()
                )
                UserInfoLine(text = stringResource(id = R.string.course_num) + " " + groupInfo.course.toString())
                UserInfoLine(
                    text = stringResource(id = R.string.updated) + " " + (if (created != null) SimpleDateFormat(
                        "dd.MM.yyyy HH:mm:ss"
                    ).format(Date(created * 1000)) else stringResource(id = R.string.unknown)).toString()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.tour_group),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(0.dp, 6.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Right
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    ScheduleManager(context).deleteSchedule(group)
                    Toast.makeText(
                        context,
                        context.getText(R.string.redownload_schedule_set),
                        Toast.LENGTH_LONG
                    ).show()
                    MainActivity.manualRecompose()
                }, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.Refresh, null)
                    Text(stringResource(id = R.string.redownload_schedule))
                }

                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    val intent = Intent(context, GroupSelectActivity::class.java)
                    context.startActivity(intent, null)
                }, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Default.Add, null)
                    Text(stringResource(id = R.string.add_group))
                }
            }
        }
    }
}


@Composable
fun GroupDropdownList(scheduleManager: ScheduleManager, currentGroup: Group) {
    val context = LocalContext.current
    var expanded by rememberSaveable { mutableStateOf(false) }
    val currentGroupName = currentGroup.name
    var selectedText by remember { mutableStateOf(currentGroupName) }

    var groupsSuggestions by remember {
        mutableStateOf(
            scheduleManager.getDownloadedSchedulesList().filter { it != currentGroupName })
    }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown


    Box(
        Modifier.padding(bottom = 16.dp)
    ) {
        OutlinedTextField(
            value = selectedText,
            enabled = false,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .clickable { if (groupsSuggestions.isNotEmpty()) expanded = !expanded },
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onBackground,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onBackground
            ),
            trailingIcon = {
                if (groupsSuggestions.isNotEmpty()) Icon(
                    icon,
                    null,
                    Modifier.clickable { if (groupsSuggestions.isNotEmpty()) expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            groupsSuggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                    Settings.setCurrentGroup(Group(label))
                    // Mai3.showToast(context.resources.getString(R.string.group_switched))
                    groupsSuggestions =
                        scheduleManager.getDownloadedSchedulesList().filter { it != label }
                    MainActivity.manualRecompose()
                },
                    text = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = label)
                            Text(
                                text = scheduleManager.getScheduleSize(Group(label))
                                    .readableFileSize(),
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                            )
                        }
                    },
                    trailingIcon = {
                        Icon(Icons.Default.Delete, null, modifier = Modifier.clickable {
                            groupsSuggestions = groupsSuggestions.filter { it != label }
                            Mai3.showToast(context.resources.getString(R.string.group_removed))
                            scheduleManager.deleteSchedule(Group(label))
                        })
                    }

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