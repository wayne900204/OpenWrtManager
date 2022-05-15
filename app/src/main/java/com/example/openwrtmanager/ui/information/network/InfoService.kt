package com.example.openwrtmanager.com.example.openwrtmanager.ui.device.network

import com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.model.InfoRequestModel
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.model.InfoResponseModelItem
import retrofit2.http.Body
import retrofit2.http.POST


// Github Doc - https://developer.github.com/v3/

interface InfoService {
    @POST("cgi-bin/luci/admin/ubus")
    suspend fun getInformation(@Body requestBody: List<InfoRequestModel>): List<InfoResponseModelItem>
}