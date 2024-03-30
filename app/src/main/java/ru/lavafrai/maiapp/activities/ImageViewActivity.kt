package ru.lavafrai.maiapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberImagePainter
import me.saket.telephoto.zoomable.ZoomSpec
import me.saket.telephoto.zoomable.rememberZoomableState
import me.saket.telephoto.zoomable.zoomable

class ImageViewActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            Column (
                Modifier.background(Color.Black).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberImagePainter(
                        intent.extras!!.getString("image")
                    ),
                    null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxSize()
                        .zoomable(rememberZoomableState(zoomSpec = ZoomSpec(4f)))
                )
            }
        }
    }
}

@Composable
fun Modifier.viewable(res: String): Modifier {
    val context = LocalContext.current

    return this.clickable {
        val intent = Intent(context, ImageViewActivity::class.java)
        intent.putExtra("image", res)
        context.startActivity(intent)
    }
}
