package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import org.jsoup.nodes.Node
import ru.lavafrai.maiapp.lavamarkup.renderer.LavamarkupRenderer

object Button : Base() {
    override val name: String = "button"

    @Composable
    override fun View(node: Node) {
        Button(onClick = { TODO("Make click handler") } ) {
            LavamarkupRenderer(node.childNodes())
        }
    }
}