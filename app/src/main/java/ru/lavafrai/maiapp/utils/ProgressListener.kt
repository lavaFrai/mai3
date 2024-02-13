package ru.lavafrai.maiapp.utils

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


class ProgressListener(val file: File, val error: (Exception) -> Unit, val after: () -> Unit, val progressCallback: (Float) -> Unit): Callback {
    override fun onFailure(call: Call, e: IOException) {
        error(e)
    }

    override fun onResponse(call: Call, response: Response) {
        if (200 == response.code) {
            var fileOutputStream: FileOutputStream? = null
            var inputStream: InputStream? = null
            try {
                val total = response.body!!.contentLength()
                var sum: Long = 0
                inputStream = response.body!!.byteStream()
                fileOutputStream = FileOutputStream(file)
                val buffer = ByteArray(2048)
                var len = 0
                while (inputStream.read(buffer).also { len = it } != -1) {
                    fileOutputStream!!.write(buffer, 0, len)

                    sum += len.toLong()
                    val progress = (sum * 1.0f / total)
                    // downloading
                    progressCallback(progress)
                }
                fileOutputStream.flush()
                after()
            } catch (e: IOException) {
                error(e)
            } finally {
                inputStream?.close()
                fileOutputStream?.close()
            }
        } else {
            error(RuntimeException())
        }
    }
}