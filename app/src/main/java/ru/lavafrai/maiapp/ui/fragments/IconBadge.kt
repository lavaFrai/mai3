package ru.lavafrai.maiapp.ui.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun IconBadge(
    icon: ImageVector = Icons.Default.Place,
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.colorScheme.primary,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Color.Transparent,
    bold: Boolean = false
) {
    Box (
        modifier = modifier
            .width(
                borderWidth
                    .times(2f)
                    .plus(30.dp)
            )
            .height(
                borderWidth
                    .times(2f)
                    .plus(30.dp)
            )
            .clip(CircleShape)
            .background(borderColor),
    ) {
        Surface(
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .clip(CircleShape)
                .align(Alignment.Center),
            color = background
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    icon,
                    null,
                    tint = color,
                    modifier = Modifier.width(20.dp).height(20.dp)
                )
            }
        }
    }
}