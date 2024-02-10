package ru.lavafrai.maiapp.ui.fragments.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.ui.fragments.text.TextH3


@Composable
fun ScheduleHeader(
    onChangeWeek: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp, 16.dp)
            .fillMaxWidth(),
    ) {
        TextH3(text = stringResource(id = R.string.schedule))
        TextButton(onClick = { onChangeWeek() }) {
            Text(text = stringResource(id = R.string.select_week))
        }
    }
}