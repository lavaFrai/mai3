package ru.lavafrai.maiapp.ui.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Preview
@Composable
fun HorizontalSeparator() {
    Box(
        modifier = Modifier
            .height(0.5.dp)
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f))
            .fillMaxWidth(1f)
    )
}