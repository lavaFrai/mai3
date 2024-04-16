package ru.lavafrai.maiapp.ui.fragments.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun PropertyString(
    name: String = "PropertyName",
    value: String = "true",
    enabled: Boolean = true,
    onChange: (String) -> Unit = {},
) {
    Row (
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name)
        OutlinedTextField(value = value, onValueChange = onChange, modifier = Modifier.weight(1f).padding(start = 16.dp))
    }
}