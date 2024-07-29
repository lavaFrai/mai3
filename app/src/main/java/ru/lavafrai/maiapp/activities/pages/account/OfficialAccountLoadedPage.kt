package ru.lavafrai.maiapp.activities.pages.account

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import compose.icons.FeatherIcons
import compose.icons.feathericons.LogOut
import ru.lavafrai.mai.api.models.Mark
import ru.lavafrai.mai.api.models.Person
import ru.lavafrai.mai.api.models.Student
import ru.lavafrai.mai.api.models.StudentMarks
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.account.AccountState
import ru.lavafrai.maiapp.data.account.OfficialAccountViewModel
import ru.lavafrai.maiapp.ui.fragments.ClickableCard
import ru.lavafrai.maiapp.ui.fragments.button.DangerButton
import ru.lavafrai.maiapp.ui.fragments.layout.PageTitle
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.widget.fragments.Separator

@Composable
fun OfficialAccountLoadedPage(viewModel: OfficialAccountViewModel, viewState: AccountState) {
    val student = viewState.student!!
    val person = viewState.person!!
    val marks = viewState.marks
    val scrollState = viewModel.getScrollState()

    PageTitle(
        title = stringResource(R.string.account),
        secondText = person.firstname,
        padded = false,
        scrollable = false,
    ) {
        Column(
            Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            PersonView(viewModel, student, person)
            MarksView(viewModel, student, person, marks)
        }
    }
}

@Composable
fun PersonView(viewModel: OfficialAccountViewModel, student: Student, person: Person) {
    ClickableCard(
        Modifier
            .padding(top = 32.dp)
            .fillMaxWidth()
    ) {
        // Users name
        OutlinedTextField(
            value = "${person.firstname} ${person.lastname}",
            enabled = false,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onBackground,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(Modifier.height(16.dp))

        val textPadding = 8.dp
        Text(student.educationLevel, modifier = Modifier.padding(bottom = textPadding))
        Text(
            "${stringResource(R.string.course_num)} ${student.course}",
            modifier = Modifier.padding(bottom = textPadding)
        )
        Text(student.department, modifier = Modifier.padding(bottom = textPadding))
        Text(
            "${stringResource(R.string.group)} ${student.group}",
            modifier = Modifier.padding(bottom = textPadding)
        )
        Text(
            "${stringResource(R.string.speciality)} ${student.specialityCipher}",
            modifier = Modifier.padding(bottom = textPadding)
        )

        Button(onClick = { viewModel.refresh() }, modifier = Modifier.fillMaxWidth()) {
            Icon(Icons.Default.Refresh, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.refresh))
        }
        DangerButton(
            onClick = { viewModel.logout() },
            dialogText = stringResource(R.string.doYouWantToSignOut),
            colors = ButtonDefaults.buttonColors(),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(FeatherIcons.LogOut, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(stringResource(R.string.signOut))
        }
    }
}

@Composable
fun MarksView(
    viewModel: OfficialAccountViewModel,
    student: Student,
    person: Person,
    studentMarks: StudentMarks?
) {
    val currentSemester = if (studentMarks != null) getCurrentSemester(studentMarks) else 0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        if (studentMarks == null) {
            CircularProgressIndicator()
            return
        }

        val semesters = studentMarks.marks.groupBy { it.semester }
        semesters.forEach {
            val semester = it.key
            val marks = it.value

            SemesterMarksView(semester, marks, currentSemester)
        }
    }
}

@Composable
fun ColumnScope.SemesterMarksView(semester: Int, marks: List<Mark>, currentSemester: Int) {
    var opened by rememberSaveable { mutableStateOf(semester == currentSemester) }
    val openIconRotation: Float by animateFloatAsState(
        if (opened) 180f else 0f,
        label = "openIconRotation"
    )

    /* Header */
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable {
                opened = !opened
            }
    ) {
        TextH3(text = "${stringResource(id = R.string.semester)} $semester")
        Icon(Icons.Default.KeyboardArrowDown, null, modifier = Modifier.rotate(openIconRotation))
    }
    Spacer(modifier = Modifier.height(8.dp))

    /* Marks */
    AnimatedVisibility(opened, enter = expandVertically()) {
        Column {
            marks.sortedBy {
                it.value.isNotBlank()
            }.forEach {
                MarkView(mark = it)
            }
        }
    }

    /* Separator */
    Separator()
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun MarkView(mark: Mark) {
    ClickableCard(
        innerPadding = 8.dp,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {
        Text(text = mark.name)
        Text(text = mark.value)
    }
}

fun getCurrentSemester(studentMarks: StudentMarks): Int {
    val maxSemester = studentMarks.marks.maxOfOrNull { it.semester }
    return maxSemester ?: 0
}