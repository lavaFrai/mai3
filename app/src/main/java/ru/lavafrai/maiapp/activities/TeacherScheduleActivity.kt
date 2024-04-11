package ru.lavafrai.maiapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.time.Date
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.api.LocalApi
import ru.lavafrai.maiapp.ui.fragments.layout.PageTitle
import ru.lavafrai.maiapp.ui.fragments.schedule.ScheduleView
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import ru.lavafrai.maiapp.utils.LoadableContent

class TeacherScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val teacherName = intent.extras!!.getString(ExtraKeys.Teacher, "Сыч Сычов Сычович")

        setContent {
            val scope = rememberCoroutineScope()

            MAI30Theme {
                Surface (modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                ) {
                    PageTitle(
                        title = stringResource(id = R.string.schedule),
                        secondText = teacherName
                    ) {
                        LoadableContent(loader = {
                            LocalApi.getTeacherScheduleOrNull(Group(teacherName))
                        }) { schedule ->
                            var scrollOffset = 0
                            schedule?.days?.forEachIndexed { index, scheduleDay ->
                                if (!scheduleDay.date!!.isEarlierThan(Date.now())) {
                                    if (scrollOffset == 0) scrollOffset = index * 3
                                    return@forEachIndexed
                                }
                            }

                            val listState = rememberLazyListState(initialFirstVisibleItemIndex = scrollOffset)

                            ScheduleView(schedule = schedule?.days, scheduleListState = listState)
                        }.View()
                    }
                }
            }
        }
    }

    companion object {
        object ExtraKeys {
            const val Teacher = "teacher"
        }
    }
}