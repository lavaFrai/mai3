package ru.lavafrai.maiapp.activities.pages.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import compose.icons.FeatherIcons
import compose.icons.feathericons.Copy
import compose.icons.feathericons.LogOut
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.account.AccountState
import ru.lavafrai.maiapp.data.account.OfficialAccountViewModel
import ru.lavafrai.maiapp.ui.fragments.button.DangerButton
import ru.lavafrai.maiapp.ui.fragments.layout.PageTitle
import ru.lavafrai.maiapp.ui.fragments.text.TextH3

@Composable
fun OfficialAccountErrorPage(
    viewModel: OfficialAccountViewModel = viewModel(),
    accountState: AccountState
) {
    /*
    Text("Error: ${accountState.error}")
    Button(onClick = { viewModel.refresh() }) {
        Text(stringResource(R.string.retry))
    }
    Button(onClick = { viewModel.logout() }) {
        Text(stringResource(R.string.signOut))
    }
    */
    PageTitle(
        title = stringResource(R.string.account),
        secondText = stringResource(id = R.string.error),
        padded = false,
        scrollable = false,
    ) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Card(
                Modifier
                    .padding(8.dp)
                    .padding(top = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Icon(Icons.Default.Warning, null, tint = MaterialTheme.colorScheme.error)
                    TextH3(text = stringResource(id = R.string.network_error))
                    Spacer(modifier = Modifier.height(8.dp))

                    val errorMessage = stringResource(id = R.string.accountNetworkError).replace(
                        "%e",
                        (accountState.error?.javaClass?.getSimpleName() ?: "null")
                    )
                    Text(
                        errorMessage.split("\n")[0],
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        modifier = Modifier.fillMaxWidth(),
                        lineHeight = 20.sp,
                        style = TextStyle.Default.copy(
                            lineBreak = LineBreak.Paragraph.copy(strategy = LineBreak.Strategy.Balanced),
                            hyphens = Hyphens.Auto,
                        ),
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        errorMessage.split("\n")[1],
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                        modifier = Modifier.fillMaxWidth(),
                        lineHeight = 20.sp,
                        style = TextStyle.Default.copy(
                            lineBreak = LineBreak.Paragraph.copy(strategy = LineBreak.Strategy.Balanced),
                            hyphens = Hyphens.Auto,
                        ),
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    val clipboardManager: ClipboardManager = LocalClipboardManager.current
                    Button(
                        onClick = {
                            clipboardManager.setText(
                                AnnotatedString(
                                    accountState.error!!.toString() + ":\n" +
                                            accountState.error.stackTraceToString()
                                )
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(FeatherIcons.Copy, null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(stringResource(id = R.string.copyTraceback))
                    }

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
        }
    }
}
