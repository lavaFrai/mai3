package ru.lavafrai.maiapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Node

@Composable
fun String?.toHypertext(): AnnotatedString {
    return  buildAnnotatedString {
        Jsoup.parse(this@toHypertext).child(0).child(1).childNodes().forEach {
            when (it.nodeName()) {
                "#text" -> append(it.preparePlainText())
                "br" -> append("")

                "a" -> append(it.preparePlainText())
                "i" -> append(it.preparePlainText())
                "b" -> append(it.preparePlainText())
                "blockquote" -> append(it.preparePlainText())
                "nobr" -> append(it.preparePlainText())
                else -> append(it.toString())
            }
        }
    }
}

fun Node.preparePlainText(): String {
    if (this.nodeName() == "#text") return this.toString().decodeEscapeCharacter().replace("\\n", "").replace("\u0097", "-")

    try {
        return this.childNodes().joinToString(""){it.preparePlainText()}
    } catch (e: Exception) {
        return " !!!${this.nodeName()}!!! "
    }
}

fun String.decodeEscapeCharacter(): String {
    return StringEscapeUtils.unescapeHtml3(this)
}