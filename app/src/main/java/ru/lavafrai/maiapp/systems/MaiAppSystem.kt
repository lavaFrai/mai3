package ru.lavafrai.maiapp.systems

import android.content.Context

open class MaiAppSystem {
    lateinit var context: Context;

    open fun init(context: Context) {
        this.context = context
    }
    open fun deinit() {}
}

enum class AppSystemName {
    AUTO_UPDATE,
    NOTIFICATIONS,
    PERMISSIONS,
}