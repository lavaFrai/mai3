package ru.lavafrai.maiapp.activities

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.lavafrai.maiapp.BuildConfig
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.Settings
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.ui.theme.MaiColor
import ru.lavafrai.maiapp.utils.LockScreenOrientation

class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        if (Settings.isApplicantMode()) { openApplicantMode() ; return }

        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(Color.Transparent, false)
            systemUiController.setNavigationBarColor(Color.Transparent, true)
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

            Surface(
                color = MaiColor,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Image(
                            painterResource(id = R.drawable.logo),
                            null,
                            modifier = Modifier.fillMaxWidth(.5f)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.2f)
                    ) {
                        Column (
                            modifier = Modifier.align(Alignment.TopCenter),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextH3(
                                text = stringResource(id = R.string.enter_as),
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            LocalButton(
                                onClick = {
                                    val intent =
                                        Intent(this@StartActivity, GroupSelectActivity::class.java)
                                    intent.putExtra(
                                        SearchActivity.ExtraKeys.Target,
                                        GroupSelectActivity.ReturnType.AddNewGroupAndOpenMainActivity
                                    )
                                    startActivity(intent)
                                    finish()
                                },
                                text = stringResource(id = R.string.student)
                            )

                            LocalButton(
                                onClick = {
                                    val intent = Intent(
                                        this@StartActivity,
                                        TeacherSelectActivity::class.java
                                    )
                                    intent.putExtra(
                                        SearchActivity.ExtraKeys.Target,
                                        TeacherSelectActivity.ReturnType.AddNewTeacherAndOpenMainActivity
                                    )
                                    startActivity(intent)
                                    finish()
                                },
                                text = stringResource(id = R.string.teacher)
                            )

                            /*LocalButton(
                                onClick = {
                                    Settings.setApplicantMode(true)
                                    openApplicantMode()
                                },
                                text = stringResource(id = R.string.applicant)
                            )*/
                        }

                        Text(
                            text = "MAI app by. lava_frai\nBuild: ${BuildConfig.BUILD_TYPE}@${BuildConfig.VERSION_NAME}",
                            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp),
                            color = Color.White.copy(alpha = .4f),
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun LocalButton(text: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults
                .buttonColors()
                .copy(containerColor = Color.White.copy(alpha = .1f)),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Row {
                Text(text = text, color = Color.White, fontSize = 20.sp)
            }
        }
    }

    private fun openApplicantMode() {
        val intent = Intent(
            this@StartActivity,
            ApplicantActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}
