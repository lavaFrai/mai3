package ru.lavafrai.maiapp.ui.fragments.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import ru.lavafrai.maiapp.R

@Composable
fun DangerButton(
    dialogText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        MaterialTheme.colorScheme.errorContainer,
        MaterialTheme.colorScheme.onErrorContainer,
    ),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    var dialogShowed by rememberSaveable { mutableStateOf(false) }
    if (dialogShowed) AlertDialog(
        icon = {
            Icon(Icons.Default.Warning, null)
        },
        title = { Text(text = stringResource(id = R.string.are_you_sure)) },
        text = { Text(text = dialogText) },
        onDismissRequest = { dialogShowed = false },
        confirmButton = {
            TextButton(
                onClick = { dialogShowed = false ; onClick() }
            ) {
                Text(stringResource(id = R.string.confirm), color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            Button(
                onClick = { dialogShowed = false }
            ) {
                Text(stringResource(id = R.string.dismiss))
            }
        }
    )

    Button(
        onClick = {
            dialogShowed = true
        },
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        content()
    }
}