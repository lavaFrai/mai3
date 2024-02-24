package ru.lavafrai.maiapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.appmetrica.analytics.AppMetrica
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.data.models.group.GroupId
import ru.lavafrai.maiapp.data.parser.parseGroupsList
import ru.lavafrai.maiapp.ui.fragments.dialogs.NetworkErrorDialog
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import kotlin.concurrent.thread

class GroupSelectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            GroupSelectView()
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun GroupSelectView() {
        val (searchBarText, setSearchBarText) = rememberSaveable { mutableStateOf("") }
        val (searchBarActive, setSearchBarActive) = rememberSaveable { mutableStateOf(false) }
        val (groupsLoaded, setGroupsLoaded) = rememberSaveable { mutableStateOf(false) }

        var groupsError by rememberSaveable { mutableStateOf(false) }

        val groups = rememberSaveable { mutableListOf<GroupId>() }
        val (selectedGroup, setSelectedGroup) = rememberSaveable { mutableStateOf<GroupId?>(null) }

        val context = LocalContext.current

        thread {
            Thread.sleep(10)
            try {
                if (!groupsLoaded) {
                    groups.addAll(parseGroupsList())
                    val tmp = groups.distinctBy { it.name }
                    groups.clear()
                    groups.addAll(tmp)
                    setGroupsLoaded(true)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                groupsError = true
                AppMetrica.reportError("Group activity network error", e)
            }
        }

        MAI30Theme (edgeToEdge = false) {
            NetworkErrorDialog(groupsError)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp, 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column (
                        Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        TextH3(
                            stringResource(id = R.string.lets_chose_group_title),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        SearchBar(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .semantics { traversalIndex = -1f }
                                .fillMaxWidth(),
                            query = searchBarText,
                            onQueryChange = { setSearchBarText(it) },
                            onSearch = { setSearchBarActive(false) },
                            active = searchBarActive,
                            onActiveChange = {
                                setSearchBarActive(it)
                            },
                            placeholder = { Text(stringResource(id = R.string.group_hint)) },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                            trailingIcon = {  },
                        ) {
                            if (searchBarText.isEmpty()) {
                                Text(
                                    text = stringResource(id = R.string.group_eg),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                            if (groupsLoaded) {
                                var foundGroups = groups.filter {
                                    it.name.contains(searchBarText, ignoreCase = true)
                                }

                                foundGroups = foundGroups.sortedBy { it.name }

                                if (foundGroups.isNotEmpty()) {
                                    foundGroups.forEach {
                                        ListItem(
                                            headlineContent = { Text(text = it.name) },
                                            modifier = Modifier.clickable {
                                                setSearchBarText(it.name)
                                                setSelectedGroup(it)
                                                setSearchBarActive(false)
                                            }
                                        )
                                    }
                                } else {
                                    Text(text = stringResource(id = R.string.group_not_found))
                                }
                            } else {
                                Column (
                                    horizontalAlignment = Alignment.CenterHorizontally,

                                ) {
                                    Text(
                                        stringResource(id = R.string.groups_loading),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        textAlign = TextAlign.Center,
                                    )

                                    CircularProgressIndicator(
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        }

                        Button(
                            onClick = {
                                Settings.setCurrentGroup(selectedGroup)

                                startActivity(Intent(
                                    this@GroupSelectActivity,
                                    MainActivity::class.java
                                ))
                                finish()
                            },
                            enabled = selectedGroup != null,
                            modifier = Modifier.padding(top = 16.dp)

                        ) {
                            Text(text = stringResource(R.string.next))
                        }
                    }
                }
            }
        }
    }
}
