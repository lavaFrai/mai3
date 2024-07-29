package ru.lavafrai.maiapp.activities.pages.account

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FeatherIcons
import compose.icons.feathericons.Eye
import compose.icons.feathericons.EyeOff
import ru.lavafrai.maiapp.R
import ru.lavafrai.maiapp.data.account.LoginCallback
import ru.lavafrai.maiapp.data.account.OfficialAccountViewModel
import ru.lavafrai.maiapp.ui.fragments.text.TextH3
import ru.lavafrai.maiapp.utils.clearFocusOnKeyboardDismiss

@Preview
@Composable
fun OfficialAccountLoginPage(viewModel: OfficialAccountViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordShowed by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf(null as String?) }

    val loginScrollState = rememberScrollState()

    val onLoginFail: LoginCallback = { message ->
        loading = false
        error = true
        errorText = message
    }

    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth(0.8f)) {
            TextH3(stringResource(id = R.string.account))
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .clearFocusOnKeyboardDismiss()
                    .fillMaxWidth(),
                value = login,
                onValueChange = { login = it ; error = false },
                label = { Text(stringResource(R.string.login)) },
                shape = MaterialTheme.shapes.large,
                singleLine = true,
                enabled = !loading,
                isError = error,
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                visualTransformation = if (passwordShowed) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .clearFocusOnKeyboardDismiss()
                    .fillMaxWidth(),
                value = password,
                onValueChange = { password = it ; error = false },
                label = { Text(stringResource(R.string.password)) },
                shape = MaterialTheme.shapes.large,
                maxLines = 1,
                trailingIcon = { ShowPasswordIcon { passwordShowed = it } },
                enabled = !loading,
                isError = error,
            )

            AnimatedVisibility(errorText != null) {
                Spacer(Modifier.height(4.dp))
                Text(
                    errorText ?: "",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(Modifier.height(16.dp))


            Button(onClick = { errorText = null ; loading = true ; viewModel.login(login.trim(), password, onLoginFail) }, modifier = Modifier.align(Alignment.End), enabled = !loading) {
                Text(stringResource(R.string.signIn))
            }
            Spacer(Modifier.height(24.dp))

            Text(
                stringResource(R.string.officialAccountLoginInfo).split("\n")[0],
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 18.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(
                stringResource(R.string.officialAccountLoginInfo).split("\n")[1],
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun ShowPasswordIcon(set: (Boolean) -> Unit) {
    var showed by remember { mutableStateOf(false) }
    Icon(
        if (showed) FeatherIcons.EyeOff else FeatherIcons.Eye,
        null,
        modifier = Modifier
            .clip(CircleShape)
            .clickable { showed = !showed; set(showed) })
}