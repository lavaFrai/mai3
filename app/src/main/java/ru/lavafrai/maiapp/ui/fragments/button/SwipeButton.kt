package ru.lavafrai.maiapp.ui.fragments.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import ru.lavafrai.maiapp.ui.fragments.button.swipeButton.SwipeButtonAnchor
import ru.lavafrai.maiapp.ui.fragments.button.swipeButton.SwipeableButton
import ru.lavafrai.maiapp.ui.fragments.button.swipeButton.SwipeableButtonState


@Composable
fun SwipeButton(
    swipeableButtonState: SwipeableButtonState,
    onClick: () -> Unit = {},
    text: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var enabled by rememberSaveable { mutableStateOf(true) }

    SwipeableButton(
        state = swipeableButtonState,
        thumbContent = { progress, targetAnchor ->
            val imageVector = when (targetAnchor.value) {
                SwipeButtonAnchor.Start -> Icons.AutoMirrored.Filled.ArrowForward
                SwipeButtonAnchor.End -> Icons.Filled.Check
            }
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        centerContent = { progress, currentAnchor ->
            Box(
                contentAlignment = Alignment.Center
            ) {
                AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut()
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                }

                AnimatedVisibility(
                    visible = !isLoading,
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut()
                ) {
                    text()
                }
            }
        },
        endContent = { progress ->
            Row {
                Icon(
                    modifier = Modifier.graphicsLayer { alpha = 1 - progress.floatValue },
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        },
        onSwiped = {
            isLoading = when (it) {
                SwipeButtonAnchor.Start -> false
                SwipeButtonAnchor.End -> true
            }
            if (isLoading) {
                enabled = false
                onClick()
            }
        }
    )
}