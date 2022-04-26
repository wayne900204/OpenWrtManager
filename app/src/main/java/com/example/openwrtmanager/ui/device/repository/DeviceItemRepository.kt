package com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository

import androidx.lifecycle.LiveData
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database.DeviceItem
import com.example.openwrtmanager.com.example.openwrtmanager.AppDatabase

class DeviceItemRepository(
    private val database: AppDatabase
) {
    suspend fun insertDeviceItem(deviceItem: DeviceItem) {
        database.deviceItemDao().insert(deviceItem)
    }

    suspend fun updateDeviceItem(deviceItem: DeviceItem) {
        database.deviceItemDao().update(deviceItem)
    }

    fun getDeviceItems(): LiveData<List<DeviceItem>> {
        return database.deviceItemDao().findAll()
    }

    suspend fun deleteDeviceItemByID(id: Int) {
        database.deviceItemDao().deleteByID(id)
    }
}