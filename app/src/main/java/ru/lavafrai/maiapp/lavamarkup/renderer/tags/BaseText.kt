package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode

object BaseText : Base() {
    override val name = "text"

    override fun match(node: Node): Boolean = node is TextNode

    @Composable
    override fun View(node: Node) {
        if ((node as TextNode).text().isNotBlank()) Text((node as TextNode).text().ifBlank { "" })
    }
}