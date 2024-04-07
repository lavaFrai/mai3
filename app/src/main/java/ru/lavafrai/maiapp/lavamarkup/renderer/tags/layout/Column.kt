package ru.lavafrai.maiapp.lavamarkup.renderer.tags.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jsoup.nodes.Node
import ru.lavafrai.maiapp.lavamarkup.renderer.LavamarkupRenderer

object Column : LayoutBase() {
    override val name: String = "column"
    override val type: LayoutBaseType = LayoutBaseType.Vertical

    @Composable
    override fun View(node: Node) {
        val modifier = Modifier.getBaseModifier(node = node)

        val (ah, av) = calculateVerticalAlignments(node = node)

        Column (
            verticalArrangement = av,
            horizontalAlignment = ah,
            modifier = modifier
        ) {
            LavamarkupRenderer(node.childNodes())
        }
    }
}