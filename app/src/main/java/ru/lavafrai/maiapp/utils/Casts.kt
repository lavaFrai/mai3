package ru.lavafrai.maiapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import ru.lavafrai.maiapp.R
import java.io.File
import java.net.URL
import java.time.DayOfWeek

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


public fun String.contains(value: String, ignoreCase: Boolean): Boolean {
    return uppercase().contains(value.uppercase())
}


fun <T> List<T>.safeSubList(fromIndex: Int, toIndex: Int): List<T> =
    this.subList(fromIndex, toIndex.coerceAtMost(this.size))


@Composable
fun DayOfWeek.localized(): String {
    return when (this) {
        DayOfWeek.MONDAY -> stringResource(id = R.string.monday)
        DayOfWeek.TUESDAY -> stringResource(id = R.string.tuesday)
        DayOfWeek.WEDNESDAY -> stringResource(id = R.string.wednesday)
        DayOfWeek.THURSDAY -> stringResource(id = R.string.thursday)
        DayOfWeek.FRIDAY -> stringResource(id = R.string.friday)
        DayOfWeek.SATURDAY -> stringResource(id = R.string.saturday)
        DayOfWeek.SUNDAY -> stringResource(id = R.string.sunday)
    }
}

/*
@Composable
fun drawableResource(): Unit? {
    return ContextCompat.getDrawable(LocalContext.current, R.mipmap.ic_launcher)?.let {
        Image(bitmap = it.toBitmap().asImageBitmap(), contentDescription = null)
    }
}*/