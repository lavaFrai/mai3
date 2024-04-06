package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import org.jsoup.nodes.Node
import ru.lavafrai.maiapp.lavamarkup.renderer.LavamarkupRenderer

object Row : Base() {
    override val name: String = "row"

    @Composable
    override fun View(node: Node) {
        Row {
            LavamarkupRenderer(node.childNodes())
        }
    }
}