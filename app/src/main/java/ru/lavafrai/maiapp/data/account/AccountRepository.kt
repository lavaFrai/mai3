package ru.lavafrai.maiapp.data.account

import android.content.Context
import kotlinx.serialization.json.Json
import ru.lavafrai.maiapp.data.models.AccountCredentials
import ru.lavafrai.maiapp.utils.decodeFromFile
import ru.lavafrai.maiapp.utils.encodeToFile
import java.io.File

class AccountRepository(val context: Context) {
    private val accountCredentialsFile: File = File(context.externalCacheDir, "account.json")

    suspend fun getActiveAccountCredentials(): AccountCredentials? {
        if (accountCredentialsFile.exists()) return Json.decodeFromFile(accountCredentialsFile)
        return null
    }

    suspend fun updateAccountCredentials(login: String, password: String): AccountCredentials {
        val accountCredentials = AccountCredentials(login, password)
        Json.encodeToFile(accountCredentials, accountCredentialsFile)
        return accountCredentials
    }

    suspend fun clearAccountCredentials() {
        if (accountCredentialsFile.exists()) accountCredentialsFile.delete()
    }
}