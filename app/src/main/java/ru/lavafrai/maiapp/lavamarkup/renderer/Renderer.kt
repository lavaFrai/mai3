package ru.lavafrai.maiapp.lavamarkup.renderer

import androidx.compose.runtime.Composable
import org.jsoup.Jsoup
import org.jsoup.nodes.Node


const val errorView = "!!!UNKNOWN TAG %!!!"

@Composable
fun LavamarkupRenderer(node: Node) {
    val tagType = Tags.find { it.match(node) }
    tagType?.View(node = node) ?: LavamarkupRenderer(errorView.replace("%", node.nodeName()))
}

@Composable
fun LavamarkupRenderer(nodes: List<Node>) {
    nodes.forEach {
        LavamarkupRenderer(node = it)
    }
}

@Composable
fun LavamarkupRenderer(string: String) {
    val soup = Jsoup.parse(string)

    LavamarkupRenderer(soup.body().childNodes())
}