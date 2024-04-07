package ru.lavafrai.maiapp.lavamarkup.renderer.tags.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jsoup.nodes.Node
import ru.lavafrai.maiapp.lavamarkup.renderer.LavamarkupRenderer

object Row : LayoutBase() {
    override val name: String = "row"
    override val type: LayoutBaseType = LayoutBaseType.Horizontal

    @Composable
    override fun View(node: Node) {
        val modifier = Modifier.getBaseModifier(node = node)

        val (ah, av) = calculateHorizontalAlignments(node = node)

        Row (
            horizontalArrangement = ah,
            verticalAlignment = av,
            modifier = modifier,
        ) {
            LavamarkupRenderer(node.childNodes())
        }
    }
}