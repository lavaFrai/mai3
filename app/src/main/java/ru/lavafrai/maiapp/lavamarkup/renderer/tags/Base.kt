package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import androidx.compose.runtime.Composable
import org.jsoup.nodes.Node

abstract class Base {
    abstract val name: String
    open fun match(node: Node): Boolean {
        return node.nodeName() == name
    }
    @Composable abstract fun View(node: Node)
}