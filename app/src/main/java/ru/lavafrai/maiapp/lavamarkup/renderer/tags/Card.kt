package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jsoup.nodes.Node
import ru.lavafrai.maiapp.lavamarkup.renderer.LavamarkupRenderer

object Card : Base() {
    override val name: String = "card"

    @Composable
    override fun View(node: Node) {
        Card (Modifier.fillMaxWidth().padding(8.dp)) {
            Column (Modifier.padding(16.dp)) {
                LavamarkupRenderer(node.childNodes())
            }
        }
    }
}