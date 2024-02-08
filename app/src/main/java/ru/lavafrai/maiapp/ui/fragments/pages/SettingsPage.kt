package ru.lavafrai.maiapp.ui.fragments.pages

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lavafrai.maiapp.GroupSelectActivity


@Preview
@Composable
fun SettingsPage() {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    Column (
        Modifier.padding(8.dp)
    ) {
        Text(text = "Settings not implemented yet")
        Text(text = "MAI 3 app by. lava_frai")
        Text(text = "Sources: https://github.com/lavaFrai/mai3")
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            activity.startActivity(Intent(
                context,
                GroupSelectActivity::class.java
            ))
            activity.finish()
        }) {
            Text(text = "Change group")
        }
    }
}