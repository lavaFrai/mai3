package ru.lavafrai.maiapp.data.filecache

import android.content.Context
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import ru.lavafrai.mai.api.network.TolerantJson
import ru.lavafrai.maiapp.utils.decodeFromFile
import ru.lavafrai.maiapp.utils.encodeToFile
import java.io.File

object FileCache {
    fun <T>store(context: Context, key: String, data: Expirable<T>) {
        val dir = context.getExternalFilesDir("cache")
        val file = File(dir, key)

        Json.encodeToFile(data, file)
    }

    inline fun <reified T>store(context: Context, key: String, data: T, expires: Long = 0) {
        val expirable = Expirable(expires, Json.encodeToString(serializer<T>(), data))

        store(context, key, expirable)
    }

    inline fun <reified T>getExpirable(context: Context, key: String): Expirable<T>? {
        val dir = context.getExternalFilesDir("cache")
        val file = File(dir, key)

        if (!file.exists()) return null

        val data = TolerantJson.decodeFromFile<Expirable<T>>(file)
        if (data.expires > now() && data.expires != 0L) return null

        return data
    }

    inline fun <reified T>get(context: Context, key: String): T? {
        return getExpirable<T>(context, key)?.data
    }

    fun now(): Long {
        return java.util.Date().time
    }
}