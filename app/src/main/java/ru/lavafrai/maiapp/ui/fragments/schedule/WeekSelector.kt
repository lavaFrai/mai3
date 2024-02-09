package ru.lavafrai.maiapp.ui.fragments.schedule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.models.schedule.ScheduleWeekId
import ru.lavafrai.maiapp.ui.fragments.PairName
import ru.lavafrai.maiapp.ui.fragments.text.TextH3


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeekSelector(
    selectorShowed: SheetState = SheetState(true),
    weeks: List<ScheduleWeekId>,
    onClose: () -> Unit = {},
    onSelect: (weekId: ScheduleWeekId) -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = onClose,
        //sheetState = selectorShowed
    ) {
        LazyColumn () {
            item {
                TextH3(
                    text = stringResource(id = R.string.select_week),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }

            items(weeks, key = {week -> week.number}) { week ->
                androidx.compose.material3.ListItem(headlineContent = {
                    Text(text = week.range.toString())
                }, modifier = Modifier.clickable {
                    onSelect(week)
                    onClose()
                }, leadingContent = {
                    PairName(text = week.number.toString())
                })
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}