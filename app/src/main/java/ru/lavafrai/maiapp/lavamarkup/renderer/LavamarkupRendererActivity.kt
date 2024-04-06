package ru.lavafrai.maiapp.lavamarkup.renderer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.ui.theme.MAI30Theme

class LavamarkupRendererActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)


        setContent {
            MAI30Theme {
                Surface(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                ) {
                    Column (Modifier.verticalScroll(rememberScrollState())) {
                        Spacer(modifier = Modifier.height(42.dp))

                        LavamarkupRenderer(
                            resources.openRawResource(R.raw.exlers_dictionary_new).reader().readText()
                        )

                        Spacer(modifier = Modifier.height(42.dp))
                    }
                }
            }
        }
    }
}