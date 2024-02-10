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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.ui.fragments.MainNavigationBar
import ru.lavafrai.maiapp.ui.fragments.MainNavigationVariants
import ru.lavafrai.maiapp.ui.fragments.pages.SchedulePage
import ru.lavafrai.maiapp.ui.fragments.pages.SettingsPage
import ru.lavafrai.maiapp.ui.theme.MAI30Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val isDarkTheme = remember { mutableStateOf<Boolean?>(Settings.getIsDarkTheme()) }
            manualRecompose = {
                isDarkTheme.value = true
                isDarkTheme.value = Settings.getIsDarkTheme()
            }

            MainView(isDarkTheme.value ?: isSystemInDarkTheme())
        }
    }


    @Composable
    fun MainView(isDarkTheme: Boolean) {
        var selectedPage by rememberSaveable { mutableStateOf(MainNavigationVariants.SCHEDULE) }

        MAI30Theme (
            darkTheme = isDarkTheme,
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
                        MainNavigationVariants.SCHEDULE -> SchedulePage()
                        MainNavigationVariants.SETTINGS -> SettingsPage()
                        else -> {}
                    }
                }
            }
        }
    }

    companion object {
        var manualRecompose: () -> Unit = { }
    }
}
