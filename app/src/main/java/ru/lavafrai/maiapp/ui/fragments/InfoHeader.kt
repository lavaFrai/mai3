package ru.lavafrai.maiapp.ui.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun InfoHeader(
    firstText: String = "Волоколамское шоссе 24Б",
    secondText: String? = "Пн-Пт 9:00-18:00 вход только с чаем",
) {
    Column (Modifier.padding(8.dp).padding(top = 16.dp)) {
        Text(text = firstText, fontSize = 20.sp)

        if (secondText != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = secondText,
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
                fontSize = 18.sp
            )
        }
    }
}