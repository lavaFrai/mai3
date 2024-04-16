package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import org.jsoup.nodes.Node
import ru.lavafrai.maiapp.lavamarkup.renderer.LavamarkupRenderer
import java.net.URL

object Button : Base() {
    override val name: String = "button"

    @Composable
    override fun View(node: Node) {
        val context = LocalContext.current

        Button(onClick = { RichTextLinkProcessor.process(context, Uri.parse(node.attr("href"))) } ) {
            LavamarkupRenderer(node.childNodes())
        }
    }
}