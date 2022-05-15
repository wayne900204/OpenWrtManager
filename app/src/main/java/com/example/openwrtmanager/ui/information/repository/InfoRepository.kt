package com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.repository

import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.network.InfoService
import com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.model.InfoRequestModel
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.model.InfoResponseModelItem

class InfoRepository(private val apiHelper: InfoService) {
    suspend fun getInformation(body:List<InfoRequestModel>): List<InfoResponseModelItem> = apiHelper.getInformation(body)
}