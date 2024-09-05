package ru.lavafrai.maiapp.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import ru.lavafrai.exler.mai.types.Teacher
import ru.lavafrai.exler.mai.types.teacherNameHash
import ru.lavafrai.mai.api.models.group.Group
import ru.lavafrai.mai.api.models.group.GroupNameAnalyzer
import ru.lavafrai.maiapp.R
import java.io.File
import java.io.InputStream
import java.net.URL
import java.text.DecimalFormat
import java.time.DayOfWeek
import kotlin.math.log10
import kotlin.math.pow

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

fun Int.toLocalizedMonthString(resources: Resources): String {
    return when (this) {
        0 -> resources.getString(R.string.january)
        1 -> resources.getString(R.string.february)
        2 -> resources.getString(R.string.march)
        3 -> resources.getString(R.string.april)
        4 -> resources.getString(R.string.may)
        5 -> resources.getString(R.string.june)
        6 -> resources.getString(R.string.july)
        7 -> resources.getString(R.string.august)
        8 -> resources.getString(R.string.september)
        9 -> resources.getString(R.string.october)
        10 -> resources.getString(R.string.november)
        11 -> resources.getString(R.string.december)
        else -> throw IllegalArgumentException("Invalid month number")
    }
}

fun Int.toLocalizedDayOfWeekString(resources: Resources): String {
    return when (this) {
        1 -> resources.getString(R.string.sunday)
        2 -> resources.getString(R.string.monday)
        3 -> resources.getString(R.string.tuesday)
        4 -> resources.getString(R.string.wednesday)
        5 -> resources.getString(R.string.thursday)
        6 -> resources.getString(R.string.friday)
        7 -> resources.getString(R.string.saturday)
        else -> throw IllegalArgumentException("Invalid day of week number")
    }
}


@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx/2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width , y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)

fun File.copyInputStreamToFile(inputStream: InputStream) {
    this.outputStream().use { fileOut ->
        inputStream.copyTo(fileOut)
    }
}


fun Long.readableFileSize(): String {
    if (this <= 0) return "0"
    val units = arrayOf("B", "kB", "MB", "GB", "TB")
    val digitGroups = (log10(this.toDouble()) / log10(1024.0)).toInt()
    return DecimalFormat("#,##0.#").format(
        this / 1024.0.pow(digitGroups.toDouble())
    ) + " " + units[digitGroups]
}

fun Teacher.fullNameEquals(another: String): Boolean {
    return teacherNameHash(another) == this.nameHash
}

fun String.fullNameEquals(another: String): Boolean {
    return teacherNameHash(another) == teacherNameHash(this)
}

fun Group.analyzeName(): GroupNameAnalyzer {
    return try {
        GroupNameAnalyzer(this.name)
    } catch (e: java.security.InvalidParameterException) {
        GroupNameAnalyzer("М0О-000С-70")
    }
}

fun <T>withMainContext(value: T, func: (T) -> Unit) {
    runBlocking {
        withContext(Dispatchers.Main) {
            func(value)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.longClickable(onLongClick: () -> Unit): Modifier {
    return this.combinedClickable(onLongClick = onLongClick) {}
}

@OptIn(ExperimentalLayoutApi::class)
fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
    var isFocused by remember { mutableStateOf(false) }
    var keyboardAppearedSinceLastFocused by remember { mutableStateOf(false) }
    if (isFocused) {
        val imeIsVisible = WindowInsets.isImeVisible
        val focusManager = LocalFocusManager.current
        LaunchedEffect(imeIsVisible) {
            if (imeIsVisible) {
                keyboardAppearedSinceLastFocused = true
            } else if (keyboardAppearedSinceLastFocused) {
                focusManager.clearFocus()
            }
        }
    }
    onFocusEvent {
        if (isFocused != it.isFocused) {
            isFocused = it.isFocused
            if (isFocused) {
                keyboardAppearedSinceLastFocused = false
            }
        }
    }
}

fun String.capitalizeFirstWord(): String {
    val first = this[0]
    return first.uppercase() + this.substring(1)
}