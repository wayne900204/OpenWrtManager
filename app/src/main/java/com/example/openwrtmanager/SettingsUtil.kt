package com.example.openwrtmanager

import android.content.Context
import android.content.pm.ApplicationInfo
import com.example.openwrtmanager.ui.device.DeviceViewModel
import java.nio.file.Path

class SettingsUtil() {
    private val TAG = SettingsUtil::class.qualifiedName
    fun localPath(): String {
        return MainApplication.applicationContext().applicationInfo.dataDir
    }
}