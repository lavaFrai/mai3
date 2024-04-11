package ru.lavafrai.maiapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.ui.fragments.dialogs.NetworkErrorDialog
import ru.lavafrai.maiapp.ui.fragments.network.NetworkErrorView
import ru.lavafrai.maiapp.ui.fragments.network.NetworkLoadingView
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import ru.lavafrai.maiapp.utils.safeSubList
import kotlin.concurrent.thread

abstract class SearchActivity<T> : ComponentActivity() {
    class ExtraKeys {
        companion object {
            val Target = "target"
        }
    }

    private var finalQuery: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MAI30Theme {
                MainView()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun MainView() {
        Column (
            Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            var queryText by rememberSaveable { mutableStateOf("") }
            var isActive by rememberSaveable { mutableStateOf(false) }
            var error by rememberSaveable { mutableStateOf(false) }
            val searchPadding by animateIntAsState(
                targetValue = if (isActive) 0 else 12,
                label = ""
            )
            var teachersList by remember { mutableStateOf<List<T>?>(listOf()) }

            select = {
                finalQuery = it
                isActive = false
                queryText = getName(it)
            }

            NetworkErrorDialog(dialogShowed = error)

            thread {
                val needed = runBlocking { withContext(Dispatchers.Main) { teachersList?.isEmpty() == true } }
                if (needed) runBlocking{
                    val tempTeacherList = getList()
                    withContext(Dispatchers.Main) {
                        teachersList = tempTeacherList
                    }
                }
            }

            SearchBar(
                query = queryText,
                onQueryChange = {queryText = it},
                active = isActive,
                onActiveChange = {isActive = it},
                onSearch = {},
                modifier = Modifier.padding(searchPadding.dp).fillMaxWidth(),
                placeholder = { Text(stringResource(id = R.string.start_typing)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            ) {
                if (teachersList?.isEmpty() == true) {
                    NetworkLoadingView()
                    return@SearchBar
                }

                if (teachersList == null) {
                    setContent {
                        NetworkErrorView()
                    }
                    return@SearchBar
                }

                search(teachersList!!, queryText).safeSubList(0, 12).forEach {
                    DrawListItem(data = it)
                }
            }


            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(bottom = 32.dp), horizontalArrangement = Arrangement.End) {
                Button(
                    onClick = {
                        onFound(finalQuery!!)
                        finish()
                    },
                    enabled = finalQuery != null
                ) {
                    Text(text = stringResource(id = R.string.next))
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.ArrowForward, null)
                }
            }
        }
    }

    lateinit var select: (T) -> Unit
    abstract fun onFound(selected: T)
    abstract fun getName(selected: T): String
    abstract fun getList(): List<T>?
    abstract fun search(list: List<T>, query: String): List<T>

    @Composable
    abstract fun DrawListItem(data: T)
}