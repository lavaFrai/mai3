package ru.lavafrai.maiapp.ui.fragments.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun PropertyBoolean(
    name: String = "PropertyName",
    isSet: Boolean = true,
    enabled: Boolean = true,
    onChange: (Boolean) -> Unit = {},
) {
    Row (
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = name)
        Switch(checked = isSet, onCheckedChange = onChange)
    }
}