package ru.lavafrai.maiapp.ui.fragments.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.ui.fragments.text.TextH3

@Preview
@Composable
fun LoadingPageView() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        TextH3(text = stringResource(R.string.schedule_loading_title))
        Text(
            text = stringResource(id = R.string.schedule_loading),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    }
}
