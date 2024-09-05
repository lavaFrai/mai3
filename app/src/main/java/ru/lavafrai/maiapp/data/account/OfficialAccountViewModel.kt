package ru.lavafrai.maiapp.data.account

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.lavafrai.mai.api.MaiAccountApi
import ru.lavafrai.mai.api.exceptions.InvalidLoginOrPasswordException
import ru.lavafrai.mai.api.models.Person
import ru.lavafrai.mai.api.models.Student
import ru.lavafrai.mai.api.models.StudentMarks
import ru.lavafrai.maiapp.data.models.AccountCredentials
import java.io.IOException
import java.nio.channels.UnresolvedAddressException


typealias LoginCallback = (String) -> Unit

class OfficialAccountViewModel(val accountRepository: AccountRepository): ViewModel() {
    private val _state = MutableStateFlow(AccountState(state = AccountState.State.Unknown))
    val state: StateFlow<AccountState> = _state.asStateFlow()
    private var scrollState = null as ScrollState?

    @Composable
    fun getScrollState(): ScrollState {
        if (scrollState == null) scrollState = rememberScrollState()
        return scrollState!!
    }

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val accountCredentials = accountRepository.getActiveAccountCredentials()
            if (accountCredentials == null) {
                _state.value = AccountState(state = AccountState.State.NotLoggedIn)
                return@launch
            }

            loadData(accountCredentials)
        }
    }

    fun login(_login: String, password: String, errorCallback: LoginCallback) {
        viewModelScope.launch {
            var login = _login
            // delay(2000)
            if (!login.endsWith("@mai.education")) {
                login += "@mai.education"
                // errorCallback("Логин должен заканчиваться на @mai.education")
                // return@launch
            }

            if (password.isBlank()) {
                errorCallback("Пожалуйста введите пароль")
                return@launch
            }

            try {
                MaiAccountApi.authorize(login, password)
            } catch (e: InvalidLoginOrPasswordException) {
                errorCallback("Неверный логин или пароль")
                return@launch
            } catch (e: UnresolvedAddressException) {
                errorCallback("Нет подключения к интернету")
                return@launch
            } catch (e: IOException) {
                errorCallback(
                    when (e.message) {
                        "Connection reset by peer" -> "Соединение сброшено (Попробуйте отключить VPN)"
                        else -> e.toString()
                    }
                )
                return@launch
            } catch (e: Exception) {
                errorCallback("Неизвестная ошибка: $e")
                return@launch
            }

            val accountCredentials = accountRepository.updateAccountCredentials(login, password)

            loadData(accountCredentials)
        }
    }

    fun logout() {
        viewModelScope.launch {
            accountRepository.clearAccountCredentials()
        }
        _state.value = AccountState(state = AccountState.State.NotLoggedIn)
    }

    private suspend fun loadData(accountCredentials: AccountCredentials) {
        _state.value = AccountState(state = AccountState.State.Loading)

        var student = null as Student?
        var person = null as Person?
        var marks = null as StudentMarks?

        val session = try {
            val session = MaiAccountApi.authorize(accountCredentials.login, accountCredentials.password)

            person = session.person()
            student = session.studentInfo().students.first()

            session
        } catch (e: InvalidLoginOrPasswordException) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.FailedToAuthorize, error = e)
            return
        } catch (e: IOException) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.NetworkError, error = e)
            return
        } catch (e: UnresolvedAddressException) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.NetworkError, error = e)
            return
        } catch (e: Exception) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.Unknown, error = e)
            return
        }

        _state.value = AccountState(state = AccountState.State.Loaded, student = student, person = person)

        marks = try {
            session.studentMarks(student.studentCode)
        } catch (e: InvalidLoginOrPasswordException) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.FailedToAuthorize, error = e)
            return
        } catch (e: IOException) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.NetworkError, error = e)
            return
        } catch (e: UnresolvedAddressException) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.NetworkError, error = e)
            return
        } catch (e: Exception) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.Unknown, error = e)
            return
        }
        _state.value = _state.value.copy(marks=marks)

        val certificates = try {
            session.certificates().certificates
        } catch (e: InvalidLoginOrPasswordException) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.FailedToAuthorize, error = e)
            return
        } catch (e: IOException) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.NetworkError, error = e)
            return
        } catch (e: UnresolvedAddressException) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.NetworkError, error = e)
            return
        } catch (e: Exception) {
            _state.value = AccountState(state = AccountState.State.Error, errorType = AccountState.Error.Unknown, error = e)
            return
        }
        _state.value = _state.value.copy(certificates=certificates)

    }

    class Factory(val context: Context): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val accountRepository = AccountRepository(context)
            return OfficialAccountViewModel(accountRepository) as T
        }
    }
}