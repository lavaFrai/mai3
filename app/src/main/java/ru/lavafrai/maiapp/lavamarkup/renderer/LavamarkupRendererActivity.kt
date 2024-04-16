package ru.lavafrai.maiapp.lavamarkup.renderer

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.lavafrai.mai.api.network.getPage
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import ru.lavafrai.maiapp.ui.utils.LoadableContentView

class LavamarkupRendererActivity : ComponentActivity() {
    companion object {
        object ExtraKeys {
            const val resource = "resource"
            const val url = "url"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val localResource = intent.extras?.getInt(ExtraKeys.resource).takeIf { it != 0 }
        val urlResource = intent.extras?.getString(ExtraKeys.url)

        setContent {
            MAI30Theme {
                Surface(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize()
                ) {
                    Column(Modifier.fillMaxSize()) {
                        LoadableContentView(loader = { loadContent(localResource, urlResource) }) { content ->
                            LavamarkupRenderer(content)
                        }
                    }
                }
            }
        }
    }

    private fun loadContent(localResource: Int?, urlResource: String?): String {
        if (localResource != null) return resources.openRawResource(localResource).reader().readText()
        if (urlResource != null) return getPage(urlResource)
        throw RuntimeException()
    }

    @Preview
    @Composable
    fun NetworkError() {
        val context = LocalContext.current
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            Icon(Icons.Default.Warning, null)
            TextH3(text = "Ошибка")
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth(0.6f)) {
                Text(
                    "При загрузке LavaMarkup страницы произошла ошибка. Возможно, указан некорректный путь или нет доступа в сеть интернет.",
                    textAlign = TextAlign.Center,
                    style = TextStyle.Default.copy(
                        lineBreak = LineBreak.Paragraph
                    ),
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { (context as Activity).finish() }) {
                Text(text = "Назад")
            }
        }
    }
}