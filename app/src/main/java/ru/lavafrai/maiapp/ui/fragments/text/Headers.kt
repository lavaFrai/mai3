package ru.lavafrai.maiapp.ui.fragments.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign


@Composable
fun TextH1(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Left
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = textAlign
    )
}

@Composable
fun TextH2(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Left
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = textAlign
    )
}

@Composable
fun TextH3(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Left
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = textAlign
    )
}
