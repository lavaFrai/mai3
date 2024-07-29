package ru.lavafrai.maiapp.ui.fragments.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.ui.fragments.button.swipeButton.SwipeButtonAnchor
import ru.lavafrai.maiapp.ui.fragments.button.swipeButton.SwipeableButton
import ru.lavafrai.maiapp.ui.fragments.button.swipeButton.rememberSwipeableButtonState

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
    val density = LocalDensity.current
    var dialogShowed by rememberSaveable { mutableStateOf(false) }
    val swipeableButtonState = rememberSwipeableButtonState(
        initialValue = SwipeButtonAnchor.Start,
        velocityThreshold = { with(density) { 400.dp.toPx() } },
    )

    if (dialogShowed) AlertDialog(
        icon = {
            Icon(Icons.Default.Warning, null)
        },
        title = { Text(text = stringResource(id = R.string.are_you_sure)) },
        text = { Text(text = dialogText) },
        onDismissRequest = { dialogShowed = false },
        confirmButton = {
            /*TextButton(
                onClick = { dialogShowed = false ; onClick() }
            ) {
                Text(stringResource(id = R.string.confirm), color = MaterialTheme.colorScheme.error)
            }*/
            
            SwipeButton(swipeableButtonState = swipeableButtonState, onClick = { dialogShowed = false ; onClick() }) {
                Text(text = stringResource(id = R.string.confirm))
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