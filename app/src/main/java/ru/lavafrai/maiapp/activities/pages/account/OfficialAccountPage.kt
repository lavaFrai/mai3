package ru.lavafrai.maiapp.activities.pages.account

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.lavafrai.maiapp.data.account.AccountState
import ru.lavafrai.maiapp.data.account.OfficialAccountViewModel


@Composable
fun OfficialAccountPage() {
    val context = LocalContext.current
    val accountViewModel: OfficialAccountViewModel = viewModel(factory = OfficialAccountViewModel.Factory(context))
    val accountState: AccountState by accountViewModel.state.collectAsState()

    when (accountState.state) {
        AccountState.State.Unknown -> {}
        AccountState.State.NotLoggedIn -> OfficialAccountLoginPage(accountViewModel)
        AccountState.State.Loaded -> OfficialAccountLoadedPage(accountViewModel, accountState)
        AccountState.State.Loading -> OfficialAccountLoadingPage(accountViewModel)
        AccountState.State.Error -> OfficialAccountErrorPage(accountViewModel, accountState)

        else -> {}
    }
}