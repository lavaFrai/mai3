package ru.lavafrai.maiapp.systems.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.lavafrai.maiapp.systems.MaiAppSystem

class PermissionsSystem : MaiAppSystem() {
    override fun init(context: Context) {
        super.init(context)


    }

    private fun requestPermission(permission: String, activity: Activity) {
        if (hasPermission(permission)) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), 101);
        }
    }

    fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) != PackageManager.PERMISSION_GRANTED
    }

    fun requestRequired(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission(Manifest.permission.POST_NOTIFICATIONS, activity)
        }
    }
}