package ru.lavafrai.maiapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valentinilk.shimmer.shimmer
import ru.lavafrai.mai.applicantsparser.Application
import ru.lavafrai.mai.applicantsparser.Prediction
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.models.ApplicantViewModel
import ru.lavafrai.maiapp.ui.fragments.schedule.PairNumber
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.ui.theme.MAI30Theme

class ApplicantActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MAI30Theme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val scrollState = rememberScrollState()
                    Scaffold() { innerPadding ->
                        val studentModeDialogVisible = rememberSaveable { mutableStateOf(false) }
                        Box {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(scrollState),
                            ) {
                                Column(
                                    Modifier
                                        .padding(innerPadding)
                                        .padding(16.dp)
                                        .padding(bottom = ButtonDefaults.MinWidth)
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    ApplicantScreen(studentModeDialogVisible)
                                }
                            }

                            ImStudentButton(
                                modifier = Modifier
                                    .padding(bottom = innerPadding.calculateBottomPadding())
                                    .align(Alignment.BottomCenter)
                                    .padding(horizontal = 16.dp)
                            ) {
                                studentModeDialogVisible.value = true
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ColumnScope.ApplicantScreen(studentModeDialogVisible: MutableState<Boolean>) {
        val applicantId = "193-082-494 83"
        StudentModeDialog(studentModeDialogVisible)

        TextH3(text = "Абитуриент")
        ApplicantLayout(applicantId)
    }

    @Composable
    fun ImStudentButton(modifier: Modifier, onClick: () -> Unit) {
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = onClick,
        ) {
            Text(text = stringResource(id = R.string.im_student))
        }
    }

    fun openStudentMode() {
        val intent = Intent(this, GroupSelectActivity::class.java)
        intent.putExtra(
            SearchActivity.ExtraKeys.Target,
            GroupSelectActivity.ReturnType.AddNewGroupAndOpenMainActivity
        )
        startActivity(intent)
    }

    @Composable
    fun StudentModeDialog(visible: MutableState<Boolean> = mutableStateOf(true)) {
        if (visible.value) AlertDialog(
            title = {
                Text(
                    text = stringResource(id = R.string.student_mode_title),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Text(
                        text = stringResource(
                            id = R.string.student_mode_text
                        )
                    )
                }
            },
            onDismissRequest = { visible.value = false },
            confirmButton = {
                Button(onClick = { openStudentMode() }) {
                    Text(
                        text = stringResource(
                            id = R.string.select_group
                        )
                    )
                }
            }
        )
    }
}

@Composable
fun ApplicantLayout(applicantId: String) {
    val applicantViewModel: ApplicantViewModel =
        viewModel(factory = ApplicantViewModel.Factory(applicantId))
    val applicant by applicantViewModel.applicantState.collectAsState()

    if (applicant == null) {
        repeat(5) {
            ApplicationShimmer()
        }
    } else {
        applicant!!.applications.sortedBy { it.priority }.forEach {
            ApplicationView(application = it, prediction = applicant!!.predictions[it.priority]!!)
        }
    }
}

@Composable
fun ApplicationShimmer() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .shimmer()
            .padding(vertical = 4.dp)
    ) {

    }
}

@Composable
fun ApplicationView(application: Application, prediction: Prediction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                PairNumber(
                    text = application.priority.toString(),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = application.filial,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = " - " + application.fullName.split(" ")[0] + ", " + application.budgetType,
                    fontSize = 18.sp
                )
            }

            val directionName = application.direction.split(" ").subList(1, application.direction.split(" ").size).joinToString(" ")
            Row(Modifier.padding(top=8.dp)) {
                Text("Место: ")
                Text(prediction.place.toString(), color = MaterialTheme.colorScheme.onBackground)
                Text("/" + prediction.placesAvailable, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f))
            }
            // Text(application.level)
            Text(directionName, lineHeight = 18.sp)
            // Text("Оригинал: " + application.hasOriginal.toString())
            // Text("Квота: " + application.quota.toString())
            // Text("Баллы: " + application.testScore.toString())
        }
    }
}