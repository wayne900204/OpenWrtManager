package com.example.openwrtmanager.com.example.openwrtmanager.ui.device

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository.AuthenticateRepository
import com.example.openwrtmanager.com.example.openwrtmanager.utils.Resource
import kotlinx.coroutines.Dispatchers

class AddDeviceViewModel(private var authenticateRepository: AuthenticateRepository) : ViewModel() {

    fun authenticate(username: String, password: String, domain:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = authenticateRepository.authenticate(username, password,domain)
            if (response.code() == 302) {
                val cookieList = response.headers().values("Set-Cookie")
                val cookieId = cookieList[0].split(";").toTypedArray()[0]
                val cookieName = cookieId.split("=").toTypedArray()[0]
                if (cookieName.equals("sysauth")) {
                    emit(Resource.success(data = cookieId.split("=").toTypedArray()[1] ))
                    return@liveData
                }
            }

            emit(Resource.error(data = null, message = "Forbidden"))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
