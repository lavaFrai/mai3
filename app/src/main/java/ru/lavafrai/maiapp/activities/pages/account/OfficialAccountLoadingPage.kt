package ru.lavafrai.maiapp.activities.pages.account

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.account.OfficialAccountViewModel
import ru.lavafrai.maiapp.ui.fragments.layout.PageTitle

@Preview
@Composable
fun OfficialAccountLoadingPage(viewModel: OfficialAccountViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    PageTitle(
        title = stringResource(R.string.account),
        secondText = "loading...",
        padded = false,
        scrollable = false,
    ) {
        Card(
            modifier = Modifier
                .padding(top = 32.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(256.dp)
                .shimmer()
        ) {

        }
    }
}
