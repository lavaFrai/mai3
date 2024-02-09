package ru.lavafrai.maiapp

import android.app.Application
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

class Mai3 : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = AppMetricaConfig
            .newConfigBuilder(BuildConfig.APP_METRIKA_API_KEY)
            .withAppVersion(getString(R.string.version))
            .withCrashReporting(true)
            .withLocationTracking(true)
            .build()
        AppMetrica.activate(this, config)
    }
}