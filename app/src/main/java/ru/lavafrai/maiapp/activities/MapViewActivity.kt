package ru.lavafrai.maiapp.activities

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import me.saket.telephoto.zoomable.ZoomSpec
import me.saket.telephoto.zoomable.rememberZoomableState
import me.saket.telephoto.zoomable.zoomable
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.ui.fragments.PageHeader
import ru.lavafrai.maiapp.ui.theme.MAI30Theme

class MapViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val resource = intent.extras?.getInt("resource") ?: R.drawable.ic_campus_map

        setContent {
            MainView(if (Settings.isDarkTheme()) R.drawable.img_campus_map_new else R.drawable.ic_campus_map)

        }
    }

    @Preview
    @Composable
    fun MainView(resource: Int = R.drawable.ic_campus_map) {
        val activity = LocalContext.current as Activity

        MAI30Theme {
            Column(
                Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                PageHeader(stringResource(id = R.string.campus_map)) { this@MapViewActivity.finish() }

                Image(
                    painterResource(resource),
                    null,
                    modifier = Modifier
                        .fillMaxSize()
                        .zoomable(rememberZoomableState(zoomSpec = ZoomSpec(6f))),
                    contentScale = ContentScale.Inside
                )
            }
        }
    }
}