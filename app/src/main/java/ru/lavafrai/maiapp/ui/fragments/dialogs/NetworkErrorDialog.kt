package ru.lavafrai.maiapp.ui.fragments.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.lavafrai.maiapp.R
import kotlin.system.exitProcess

@Composable
fun NetworkErrorDialog(dialogShowed: Boolean) {
    if (dialogShowed) AlertDialog(
        icon = {
            Icon(Icons.Default.Warning, null)
        },
        title = { Text(text = stringResource(id = R.string.network_error)) },
        text = { Text(text = stringResource(id = R.string.network_error_description)) },
        onDismissRequest = { },
        confirmButton = {
            TextButton(
                onClick = { exitProcess(0) }
            ) {
                Text(stringResource(id = R.string.confirm), color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = { }
    )
}