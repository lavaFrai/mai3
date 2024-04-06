package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import ru.lavafrai.maiapp.lavamarkup.renderer.error.ParseException
import ru.lavafrai.maiapp.ui.theme.MaiColor
import java.net.URL


/**
 * Предоставляет возможность выводить форматируемый текст, в котором доступны теги форматирования,
 * подобные обычному markdown по функционалу, не поддерживает вложенность других тегов, не из
 * RichText scope.
 * <br>
 * RichText scope содержит следующие теги:
 * i - курсивный текст,
 * b - более "жирный" текст,
 * s - перечеркнутый текст,
 * f - изменение размера шрифта в аттрибуте size,
 * c - изменение цвета шрифта в аттрибуте color. Пример: color = "primary". Так же возможны: primary, onPrimary, secondary, secondary70, onSecondary, error, onError,
 * a - текст синегоо текста, означающий гиперссылку, аттрибут href - ссылка
 * br - перенос строки
 * */
object RichText : Base() {
    override val name: String = "rich"

    @Composable
    override fun View(node: Node) {
        val context: Context = LocalContext.current

        val textStile = LocalTextStyle.current.toSpanStyle().copy(
            color = MaterialTheme.colorScheme.onBackground
        )
        val annotatedText = buildAnnotatedString {
            pushStyle(textStile)
            append(buildRichText(node.childNodes()))
            pop()
        }

        ClickableText(
            annotatedText, onClick = { offset ->
                val annotation = annotatedText.getStringAnnotations(tag = "url", offset, offset).firstOrNull()
                annotation?.let {
                    RichTextLinkProcessor.process(context, URL(annotation.item))
                }
            })
    }

    @Composable
    private fun buildRichText(node: Node): AnnotatedString {
        val colorScheme = MaterialTheme.colorScheme

        return buildAnnotatedString {
            if (node is TextNode) append(node.text())

            else when (node.nodeName()) {
                "i" -> {
                    pushStyle(SpanStyle(fontStyle = FontStyle.Italic))
                    append(buildRichText(node.childNodes()))
                    pop()
                }

                "b" -> {
                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
                    append(buildRichText(node.childNodes()))
                    pop()
                }

                "f" -> {
                    val size = try { node.attr("size").toInt() } catch (e: NumberFormatException) { 12 }
                    pushStyle(SpanStyle(fontSize = size.sp))
                    append(buildRichText(node.childNodes()))
                    pop()
                }

                "c" -> {
                    val color = when (node.attr("color")) {
                        "primary" -> colorScheme.primary
                        "onPrimary" -> colorScheme.onPrimary
                        "secondary" -> colorScheme.secondary
                        "secondary70" -> colorScheme.secondary.copy(alpha = 0.7f)
                        "onSecondary" -> colorScheme.onSecondary
                        "error" -> colorScheme.error
                        "onError" -> colorScheme.onError
                        else -> androidx.compose.ui.graphics.Color.Red
                    }
                    pushStyle(SpanStyle(color = color))
                    append(buildRichText(node.childNodes()))
                    pop()
                }

                "s" -> {
                    pushStyle(SpanStyle(textDecoration = TextDecoration.LineThrough))
                    append(buildRichText(node.childNodes()))
                    pop()
                }

                "a" -> {
                    val hasHref = node.attr("href").isNotBlank()
                    val href = if (hasHref) URL(node.attr("href")) else URL("http://example.com/")

                    if (hasHref) pushStringAnnotation("url", node.attr("href"))
                    pushStyle(
                        SpanStyle(
                            textDecoration = TextDecoration.Underline,
                            color = MaiColor
                        )
                    )
                    append(buildRichText(node.childNodes()))
                    pop()
                    if (hasHref) pop()
                }

                "br" -> {
                    append("\n")
                }

                else -> throw ParseException("Failed to parse tag ${node.nodeName()} in rich text scope")
            }
        }
    }

    @Composable
    private fun buildRichText(nodes: List<Node>): AnnotatedString {
        return buildAnnotatedString {
            nodes.forEach {
                append(buildRichText(it))
            }
        }
    }
}

object RichTextLinkProcessor {
    fun process(context: Context, url: URL) {
        when (url.protocol) {
            "lmn" -> {
                (context as Activity).finish()
                TODO("Open new lavamarkup view activity")
            }
            "lm" -> {
                TODO("Open new lavamarkup view activity")
            }
            else -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
                startActivity(context, browserIntent, null)
            }
        }
    }
}