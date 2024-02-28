package ru.lavafrai.maiapp

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import ru.lavafrai.maiapp.ui.theme.MaiColor

class SplashActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContent {
            MAI30Theme {
                SplashView()
            }
        }
    }

    @Preview(
        device = "spec:width=1080px,height=2400px,dpi=440",
        uiMode = Configuration.UI_MODE_NIGHT_YES
    )
    @Composable
    fun SplashView() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),

        ) {
            Card(
                Modifier
                    .width(128.dp)
                    .height(128.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(25)),
                colors = CardDefaults.cardColors().copy(containerColor = MaiColor)
            ) {
            }
        }
    }
}