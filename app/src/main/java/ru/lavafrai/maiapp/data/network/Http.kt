package ru.lavafrai.maiapp.data.network

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.StringWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.Scanner





/**
 * Trying to download url
 * @return downloaded content or null if error occurred
 */
fun httpGet(url: URL) : String? {
    val urlConnection = url.openConnection() as HttpURLConnection

    return try {
        val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
        val outputStringWriter = StringWriter()

        IOUtils.copy(inputStream, outputStringWriter, Charsets.UTF_8)

        outputStringWriter.toString()
    } catch (e: Exception) {

        null
    } finally {
        urlConnection.disconnect()
    }
}
