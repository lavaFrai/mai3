package ru.lavafrai.maiapp.lavamarkup.renderer.tags.layout

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jsoup.nodes.Node
import ru.lavafrai.maiapp.lavamarkup.renderer.tags.Base

abstract class LayoutBase : Base() {
    enum class LayoutBaseType {
        Vertical,
        Horizontal
    }

    abstract val type: LayoutBaseType

    @Composable
    fun Modifier.getBaseModifier(node: Node): Modifier {
        var modifier = this

        if (node.attr("width") == "fill") modifier = modifier.fillMaxWidth()
        if (node.attr("height") == "fill") modifier = modifier.fillMaxHeight()

        if (node.hasAttr("scroll") && type == LayoutBaseType.Horizontal) modifier = modifier.horizontalScroll(rememberScrollState())
        if (node.hasAttr("scroll") && type == LayoutBaseType.Vertical) modifier = modifier.verticalScroll(rememberScrollState())
        if (node.hasAttr("padding")) modifier = modifier.padding((node.attr("padding").toIntOrNull() ?: 0).dp)

        return modifier
    }

    @Composable
    fun calculateVerticalAlignments(node: Node): Pair<Alignment.Horizontal, Arrangement.Vertical> {
        val av: Arrangement.Vertical = when (node.attr("vertical").lowercase()) {
            "center" -> Arrangement.Center
            "bottom" -> Arrangement.Bottom
            "top" -> Arrangement.Top
            "spacebetween" -> Arrangement.SpaceBetween
            "spacearound" -> Arrangement.SpaceAround
            "spaceevenly" -> Arrangement.SpaceEvenly
            else -> Arrangement.Top
        }

        val ah: Alignment.Horizontal = when (node.attr("horizontal").lowercase()) {
            "center" -> Alignment.CenterHorizontally
            "end" -> Alignment.End
            "start" -> Alignment.Start
            else -> Alignment.Start
        }

        return ah to av
    }

    @Composable
    fun calculateHorizontalAlignments(node: Node): Pair<Arrangement.Horizontal, Alignment.Vertical> {
        val av: Alignment.Vertical = when (node.attr("vertical").lowercase()) {
            "center" -> Alignment.CenterVertically
            "bottom" -> Alignment.Bottom
            "top" -> Alignment.Top
            else -> Alignment.Top
        }

        val ah: Arrangement.Horizontal = when (node.attr("horizontal").lowercase()) {
            "start" -> Arrangement.Start
            "end" -> Arrangement.End
            "spacebetween" -> Arrangement.SpaceBetween
            "spacearound" -> Arrangement.SpaceAround
            "spaceevenly" -> Arrangement.SpaceEvenly
            else -> Arrangement.Start
        }

        return ah to av
    }
}