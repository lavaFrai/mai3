package ru.lavafrai.maiapp.utils

import android.content.Context
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Node
import ru.lavafrai.maiapp.ui.theme.MaiColor

@Composable
fun String?.toHypertext(): AnnotatedString {
    return  buildAnnotatedString {
        append(Jsoup.parse(this@toHypertext).child(0).child(1).preparePlainText())
    }
}

@Composable
fun Node.preparePlainText(): AnnotatedString {
    if (this.nodeName() == "#text") return AnnotatedString(
        this.toString()
            .decodeEscapeCharacter()
            .replace("\\n", "")
            .replace("\u0097", "-")
            .replace("\n", "")
            .replace("  ", " ")
    )
/*
    return try {
        this.childNodes().joinToString(""){it.preparePlainText()}
    } catch (e: Exception) {
        " !!!${this.nodeName()}!!! "
    }*/

    return buildAnnotatedString {
        this@preparePlainText.childNodes().forEach {
            when (it.nodeName()) {
                "#text" -> append(it.preparePlainText())
                "br" -> append("\n")

                "a" -> {
                    pushStringAnnotation(tag = "a", annotation =
                        if (it.attr("href").startsWith("http")) it.attr("href")
                        else if (it.attr("href").startsWith("/")) "https://mai-exler.ru/" + it.attr("href")
                        else "https://example.com"
                    )
                    withStyle(style = SpanStyle(color = MaiColor, textDecoration = TextDecoration.Underline)) {
                        append(it.preparePlainText())
                    }
                    pop()
                }

                "i" -> {
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                        append(it.preparePlainText())
                    }
                }

                "b" -> {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(it.preparePlainText())
                    }
                }

                "blockquote" -> append(it.preparePlainText())
                "nobr" -> append(it.preparePlainText())
                else -> append(it.toString())
            }
        }
    }
}

fun String.decodeEscapeCharacter(): String {
    return StringEscapeUtils.unescapeHtml3(this)
}

fun processHypertextStringAnnotation(context: Context, string: AnnotatedString, offset: Int) {
    string.getStringAnnotations(tag = "a", start = offset, end = offset).firstOrNull()?.let {
        CustomTabs.openTab(context, it.item)
    }
}


@Composable
fun Hypertext(text: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onBackground, ) {
    val hypertext = text.toHypertext()
    val context = LocalContext.current

    ClickableText(
        hypertext,
        style = LocalTextStyle.current.copy(color = color),
        modifier = modifier
    ) {
        offset -> processHypertextStringAnnotation(context, hypertext, offset)
    }
}