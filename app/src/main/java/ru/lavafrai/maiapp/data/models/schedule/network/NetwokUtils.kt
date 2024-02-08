package ru.lavafrai.maiapp.data.models.schedule.network

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import ru.lavafrai.maiapp.data.COOKIES_CONSTANT
import ru.lavafrai.maiapp.data.SCHEDULE_PAGE_URL

fun getPage(url: String): Document {
    return Jsoup
        .connect(url)
        .header("Cookie", COOKIES_CONSTANT)
        .get()
}

fun getSchedulePage(args: Map<String, String>): Document {
    var url = "$SCHEDULE_PAGE_URL?"
    args.forEach {
        url += "${it.key}=${it.value}&"
    }

    return getPage(url)
}
