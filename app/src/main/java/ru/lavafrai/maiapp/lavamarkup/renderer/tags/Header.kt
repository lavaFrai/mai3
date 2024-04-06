package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import org.jsoup.nodes.Node
import ru.lavafrai.maiapp.lavamarkup.renderer.LavamarkupRenderer

object Header : Base() {
    override val name: String = "header"

    @Composable
    override fun View(node: Node) {
        val textStyle = TextStyle(fontSize = 24.sp)
        val mergedStyle = LocalTextStyle.current.merge(textStyle)
        CompositionLocalProvider(LocalTextStyle provides mergedStyle) {
            LavamarkupRenderer(node.childNodes())
        }
    }
}