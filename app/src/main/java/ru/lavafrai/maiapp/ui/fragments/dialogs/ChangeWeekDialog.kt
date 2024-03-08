package ru.lavafrai.maiapp.ui.fragments.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.lavafrai.mai.api.models.schedule.ScheduleWeekId
import ru.lavafrai.maiapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeWeekDialog(
    weeks: List<ScheduleWeekId>,
    onClose: () -> Unit = {},
    onSelect: (weekId: ScheduleWeekId?) -> Unit = {},
    onOpenWeekSelector: () -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = onClose,
        //sheetState = selectorShowed // Disabled cause it break everything by unknown reason
    ) {
        DialogItem(icon = Icons.Default.ArrowBack, text = stringResource(id = R.string.last_week), modifier = Modifier.clickable {
            val index = weeks.indexOfFirst { it.range.isNow() }
            onSelect( weeks.getOrNull(index - 1) )
            onClose()
        })
        DialogItem(icon = Icons.Default.Home, text = stringResource(id = R.string.current_week), modifier = Modifier.clickable {
            onSelect( weeks.find { it.range.isNow() } )
            onClose()
        })
        DialogItem(icon = Icons.Default.ArrowForward, text = stringResource(id = R.string.next_week), modifier = Modifier.clickable {
            val index = weeks.indexOfFirst { it.range.isNow() }
            onSelect( weeks.getOrNull(index + 1) )
            onClose()
        })
        DialogItem(icon = Icons.Default.DateRange, text = stringResource(id = R.string.other_week), modifier = Modifier.clickable {
            onOpenWeekSelector()
            onClose()
        })
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun DialogItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon, null, modifier = Modifier.padding(16.dp), tint = MaterialTheme.colorScheme.primary)
        Text(text = text)
    }
}