package ru.lavafrai.maiapp.data.network

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.StringWriter
import java.net.HttpURLConnection
import java.net.URL


/**
 * Trying to download url
 * @return downloaded content or null if error occurred
 */
fun httpGet(url: URL, attemptsLeft: Int = 5) : String? {
    if (attemptsLeft <= 0) return null;


    return try {
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        try {
            val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
            val outputStringWriter = StringWriter()

            IOUtils.copy(inputStream, outputStringWriter, Charsets.UTF_8)

            outputStringWriter.toString()
        } finally {
            urlConnection.disconnect()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        httpGet(url, attemptsLeft - 1)
    }
}
