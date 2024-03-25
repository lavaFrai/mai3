package ru.lavafrai.maiapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.lavafrai.maiapp.R

interface Filterer<T> {
    fun filter(value: T): Boolean

    @Composable
    fun getName(): String {
        return stringResource(id = R.string.unknown)
    }
}

fun <T>filterer(_filter: (T) -> Boolean): Filterer<T> {
    return object : Filterer<T> {
        override fun filter(value: T): Boolean {
            return _filter(value)
        }
    }
}