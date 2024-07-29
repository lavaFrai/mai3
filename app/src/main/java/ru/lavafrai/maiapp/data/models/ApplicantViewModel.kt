package ru.lavafrai.maiapp.data.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.lavafrai.mai.api.network.TolerantJson
import ru.lavafrai.mai.applicantsparser.ApplicantWithPrediction
import java.io.IOException

class ApplicantViewModel(
    val id: String,
): ViewModel() {
    private var _applicantState = MutableStateFlow(null as ApplicantWithPrediction?)
    val applicantState = _applicantState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            val client = HttpClient(CIO) {
                expectSuccess = true
            }   

            var response = try {
                client.get("https://mai3.lavafrai.ru/applicant/$id")
            } catch (e: IOException) {
                null
            }

            while(response == null || response.status.value != 200) {
                response = try {
                    client.get("https://mai3.lavafrai.ru/applicant/$id")
                } catch (e: IOException) {
                    null
                }
            }

            _applicantState.value = TolerantJson.decodeFromString(response.bodyAsText())
        }
    }

    class Factory(val id: String): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T = ApplicantViewModel(id) as T
    }
}
