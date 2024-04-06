package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jsoup.nodes.Node

object Br : Base() {
    override val name: String = "br"

    @Composable
    override fun View(node: Node) {
        val size = if (node.attr("height").isBlank()) {
            0
        } else {
            try {
                node.attr("height").toInt()
            } catch (e: NumberFormatException) {
                0
            }
        }
        Spacer(modifier = Modifier.height(size.dp))
    }
}