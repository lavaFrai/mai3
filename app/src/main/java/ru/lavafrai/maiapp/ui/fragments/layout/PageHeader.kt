package ru.lavafrai.maiapp.ui.fragments.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lavafrai.maiapp.ui.fragments.text.TextH3


@Deprecated("PageTitle is actual now")
@Preview
@Composable
fun PageHeader(title: String = "Map", onClose: () -> Unit = {}) {
    Column (
        Modifier.padding(top = 24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Absolute.Left,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp, 16.dp)
                .fillMaxWidth(),
        ) {
            IconButton(onClick = onClose) {
                Icon(Icons.Default.ArrowBack, null, tint = MaterialTheme.colorScheme.onBackground)
            }
            TextH3(text = title, color = MaterialTheme.colorScheme.onBackground)
        }

        Box(
            modifier = Modifier
                .height(0.5.dp)
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
        )
    }
}