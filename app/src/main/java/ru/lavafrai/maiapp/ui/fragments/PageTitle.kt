package ru.lavafrai.maiapp.ui.fragments

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lavafrai.maiapp.ui.fragments.text.TextH3

@Preview
@Composable
fun PageTitle(
    title: String = "Title",
    secondText: String? = null,
    buttonText: String? = null,
    onButtonClicked: () -> Unit = {},
    scrollable: Boolean = false,
    backButton: Boolean = false,
    padded: Boolean = true,
    content: @Composable (Modifier) -> Unit = {}
) {
    val context = LocalContext.current
    Box(Modifier.background(MaterialTheme.colorScheme.background)) {
        Column(
            if (scrollable) Modifier
                .padding(top = if (padded) 28.dp else 0.dp)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .verticalScroll(rememberScrollState(), enabled = scrollable)
            else Modifier
                .padding(top = if (padded) 28.dp else 0.dp)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp, 16.dp)
                    .fillMaxWidth(),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    0
                    if (backButton) IconButton(onClick = { (context as Activity).finish() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    Column {
                        TextH3(text = title, color = MaterialTheme.colorScheme.onBackground)
                        if (secondText != null) Text(
                            text = secondText,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Light
                        )
                    }
                }

                if (buttonText != null) {
                    TextButton(onClick = { onButtonClicked() }) {
                        Text(buttonText)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .height(0.5.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
            )

            content(Modifier)
        }
    }
}
