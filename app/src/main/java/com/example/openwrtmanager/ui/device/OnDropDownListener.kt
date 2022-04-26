package com.example.openwrtmanager.com.example.openwrtmanager.ui.device

import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database.DeviceItem


interface OnDropDownListener {
    fun onChange(deviceItem: Array<String>)
}