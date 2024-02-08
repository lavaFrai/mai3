package ru.lavafrai.maiapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.net.URL

fun String.toURL() : URL {
    return URL(this)
}

fun <A, B>List<A>.mapThreaded (f: suspend (A) -> B): List<B> = runBlocking {
    map {
        async(Dispatchers.Default)
        {
            f(it)
        }
    }.map { it.await() }
}


@OptIn(ExperimentalSerializationApi::class)
public inline fun <reified T> Json.decodeFromFile(file: File): T {
    val stream = file.inputStream()
    val value = Json.decodeFromStream<T>(stream)
    stream.close()
    return value
}


@OptIn(ExperimentalSerializationApi::class)
public inline fun <reified T> Json.encodeToFile(value: T, file: File) {
    val stream = file.outputStream()
    Json.encodeToStream(value, stream)
    stream.close()
}
