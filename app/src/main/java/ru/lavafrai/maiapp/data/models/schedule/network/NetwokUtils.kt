package ru.lavafrai.maiapp.data.models.schedule.network

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import ru.lavafrai.maiapp.data.COOKIES_CONSTANT
import ru.lavafrai.maiapp.data.SCHEDULE_PAGE_URL
import java.io.IOException

fun getPage(url: String, args: Map<String, String> = mapOf(), attemptsLeft: Int = 50): Document {
    var builtUrl = url
    if (!builtUrl.endsWith("?")) builtUrl += "?"
    args.forEach {
        builtUrl += "&${it.key}=${it.value}"
    }
    if (attemptsLeft < 0) throw IOException()

    try {
        return Jsoup
            .connect(builtUrl)
            .header("Cookie", COOKIES_CONSTANT)
            .get()
    } catch (e: Exception) {
        e.printStackTrace()
        Thread.sleep(100)
        return getPage(url, args, attemptsLeft - 1)
    }
}

fun getSchedulePage(args: Map<String, String> = mapOf()): Document {
    var url = "$SCHEDULE_PAGE_URL?"
    args.forEach {
        url += "${it.key}=${it.value}&"
    }

    return getPage(url)
}
