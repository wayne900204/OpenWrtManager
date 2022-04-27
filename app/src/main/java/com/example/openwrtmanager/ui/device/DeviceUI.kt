package com.example.openwrtmanager.com.example.openwrtmanager.ui.device

import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database.DeviceItem


interface DeviceUI {
    fun onIdentityItemsChange(items: Array<String>)
    fun onDeviceItemChange(items: DeviceItem)
}
