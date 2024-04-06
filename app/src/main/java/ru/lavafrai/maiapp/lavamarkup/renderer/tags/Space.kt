package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jsoup.nodes.Node

object Space : Base() {
    override val name: String = "space"

    @Composable
    override fun View(node: Node) {
        val size = if (node.attr("height").isBlank()) { 8 } else {
            try {
                node.attr("height").toInt()
            } catch (e: NumberFormatException) {
                0
            }
        }
        Spacer(modifier = Modifier.height(size.dp).width(size.dp))
    }
}