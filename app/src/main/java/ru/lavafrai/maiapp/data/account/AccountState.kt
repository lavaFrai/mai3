package ru.lavafrai.maiapp.data.account

import ru.lavafrai.mai.api.models.Certificate
import ru.lavafrai.mai.api.models.Person
import ru.lavafrai.mai.api.models.Student
import ru.lavafrai.mai.api.models.StudentMarks

data class AccountState(
    val state: State,
    val error: Exception? = null,
    val errorType: Error? = null,
    val person: Person? = null,
    val student: Student? = null,
    val marks: StudentMarks? = null,
    val certificates: List<Certificate>? = null,
) {

    enum class State {
        Unknown,
        NotLoggedIn,
        Loading,
        Loaded,
        Error,
    }

    enum class Error {
        FailedToAuthorize,
        NetworkError,
        Unknown,
    }
}