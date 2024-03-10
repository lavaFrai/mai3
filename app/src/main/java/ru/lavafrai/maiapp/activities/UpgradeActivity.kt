package ru.lavafrai.maiapp.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import ru.lavafrai.maiapp.BuildConfig
import ru.lavafrai.maiapp.Mai3
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.ACTUAL_VERSION_FILE_URL
import ru.lavafrai.maiapp.data.ACTUAL_VERSION_INFO_URL
import ru.lavafrai.maiapp.data.models.ActualVersionInfo
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.ui.theme.MAI30Theme
import ru.lavafrai.maiapp.utils.MyTrustManager
import ru.lavafrai.maiapp.utils.ProgressListener
import java.io.File
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager
import kotlin.concurrent.thread

class UpgradeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MAI30Theme {
                MainView()
            }
        }
    }


    @Preview
    @Composable
    fun MainView() {
        var state by rememberSaveable { mutableStateOf(State.CHECKING_FOR_UPGRADES) }
        var actualVersionInfo by remember { mutableStateOf(ActualVersionInfo()) }
        var progress by remember { mutableFloatStateOf(0f) }

        thread {
            getActualVersionInfo(error = {
                state = State.NETWORK_ERROR
            }) {
                if (it.actual != BuildConfig.VERSION_NAME) {
                    actualVersionInfo = it
                    state = State.UPGRADE_PREVIEW
                } else {
                    state = State.UP_TO_DATE
                }
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {
                    TextH3(text = stringResource(id = R.string.upgrade))
                    Spacer(modifier = Modifier.height(16.dp))

                    when (state) {
                        State.CHECKING_FOR_UPGRADES -> CheckingForUpgrades()
                        State.UPGRADE_PREVIEW -> UpgradePreview(actualVersionInfo) {
                            state = State.DOWNLOADING
                            startDownloading(
                                actualVersionInfo,
                                progress = {progress = it},
                                error = { state = State.NETWORK_ERROR }) {
                                    state = State.DOWNLOADED
                            }
                        }

                        State.DOWNLOADING -> Downloading(progress)
                        State.DOWNLOADED -> Downloaded {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.setDataAndType(Uri.fromFile(File(Mai3.filesPath, "actual.apk")), "application/vnd.android.package-archive")
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            /* TODO copy file to downloads and than run */
                            startActivity(intent)
                        }
                        State.UP_TO_DATE -> UpToDate()
                        else -> NetworkError()
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun NetworkError() {
        Column {
            Text(stringResource(id = R.string.network_error_ocured))
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Refresh, null)
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }

    @Preview
    @Composable
    fun CheckingForUpgrades() {
        Column {
            Text(stringResource(id = R.string.checking_for_upgrades))
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    @Preview
    @Composable
    fun UpToDate() {
        Column {
            Text(stringResource(id = R.string.up_to_date))
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

    @Preview
    @Composable
    fun UpgradePreview(
        actualVersionInfo: ActualVersionInfo = ActualVersionInfo(),
        download: () -> Unit = {}
    ) {
        Column {
            Text(stringResource(id = R.string.version_ready_to_download))
            Text(stringResource(id = R.string.current_version) + ": " + BuildConfig.VERSION_NAME)
            Text(stringResource(id = R.string.actual_version) + ": " + actualVersionInfo.actual)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = download) {
                Text(text = stringResource(id = R.string.download))
            }
        }
    }

    @Preview
    @Composable
    fun Downloaded(install: () -> Unit = {}) {
        Column {
            Text(stringResource(id = R.string.version_ready_install))
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = install) {
                Text(text = stringResource(id = R.string.upgrade))
            }
        }
    }

    @Preview
    @Composable
    fun Downloading(progress: Float = 0.1f) {
        Column {
            Text(stringResource(id = R.string.downloading_upgrade))
            Spacer(modifier = Modifier.height(16.dp))
            val animatedProgress by animateFloatAsState(
                targetValue = progress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
                label = ""
            )
            LinearProgressIndicator(progress = {animatedProgress}, Modifier.fillMaxWidth(), )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    enum class State {
        CHECKING_FOR_UPGRADES,
        UPGRADE_PREVIEW,
        DOWNLOADING,
        NETWORK_ERROR,
        DOWNLOADED,
        UP_TO_DATE,
    }

    private fun getActualVersionInfo(
        error: (Exception) -> Unit,
        after: (ActualVersionInfo) -> Unit
    ) {
        try {
            val request = Jsoup
                .connect(ACTUAL_VERSION_INFO_URL)
                .validateTLSCertificates(false)
                .ignoreContentType(true)
                .execute()
            val response = Json.decodeFromString<ActualVersionInfo>(request.body())
            after(response)
        } catch (e: Exception) {
            e.printStackTrace()
            error(e)
        }
    }

    private fun startDownloading(
        actualVersionInfo: ActualVersionInfo,
        progress: (Float) -> Unit,
        error: (Exception) -> Unit,
        after: () -> Unit
    ) {
        thread {
            try {
                val trustAllCerts = arrayOf(MyTrustManager())

                val sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, SecureRandom())
                val sslSocketFactory = sslContext.socketFactory;

                val request: Request = Request.Builder()
                    .url(ACTUAL_VERSION_FILE_URL)
                    .build()

                val client = OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(300, TimeUnit.SECONDS)
                    .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                    .build()

                val temporaryFile = File(Mai3.filesPath, "actual.apk")
                if (temporaryFile.exists()) temporaryFile.delete()

                client.newCall(request).enqueue( ProgressListener(
                    file = temporaryFile,
                    progressCallback = progress,
                    after = after,
                    error = error
                ) )
                //if (!response.isSuccessful) throw IOException("Unexpected code $response");

                //temporaryFile.copyInputStreamToFile(response.body!!.byteStream())

            } catch (e: Exception) {
                e.printStackTrace()
                error(e)
            }
        }
    }
}