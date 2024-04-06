package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import org.jsoup.nodes.Node
import ru.lavafrai.maiapp.lavamarkup.renderer.LavamarkupRenderer

object Action : Base() {
    override val name: String = "action"

    @Composable
    override fun View(node: Node) {
        val context = LocalContext.current
        val action = node.attr("type").ifBlank { null }
        var runnable = null as (() -> Unit)?

        when (action) {
            "close" -> runnable = { (context as Activity).finish() }
            "url" -> runnable = {
                val url = node.attr("url").ifBlank { "https://example.com/" }
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                ContextCompat.startActivity(context, browserIntent, null)
            }
        }

        Box(modifier = Modifier.clickable{ runnable?.let { runnable() } } ) {
            LavamarkupRenderer(node.childNodes())
        }
    }
}