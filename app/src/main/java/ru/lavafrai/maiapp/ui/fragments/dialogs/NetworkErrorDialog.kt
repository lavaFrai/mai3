package ru.lavafrai.maiapp.ui.fragments.dialogs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ru.lavafrai.maiapp.Mai3
import ru.lavafrai.maiapp.R

@Composable
fun NetworkErrorDialog(dialogShowed: Boolean) {
    val context = LocalContext.current

    if (dialogShowed) AlertDialog(
        icon = {
            Icon(Icons.Default.Warning, null)
        },
        title = { Text(text = stringResource(id = R.string.network_error)) },
        text = { Text(text = stringResource(id = R.string.network_error_description)) },
        onDismissRequest = { },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ),
                onClick = { Mai3.restart(context) }
            ) {
                Text(stringResource(id = R.string.restart), color = MaterialTheme.colorScheme.onError)
            }
        },
        dismissButton = {
            TextButton(
                onClick = { Mai3.wipeData() ; Mai3.restart(context) }
            ) {
                Text(stringResource(id = R.string.restart_and_wipe_data), color = MaterialTheme.colorScheme.error)
            }
        }
    )
}