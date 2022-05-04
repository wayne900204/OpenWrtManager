package com.example.openwrtmanager.com.example.openwrtmanager.ui.device

import androidx.lifecycle.*
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository.AuthenticateRepository
import com.example.openwrtmanager.com.example.openwrtmanager.utils.Resource
import com.example.openwrtmanager.com.example.openwrtmanager.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddDeviceViewModel(private val authenticateRepository: AuthenticateRepository) : ViewModel() {

    fun authenticate(username: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = authenticateRepository.authenticate(username, password)
            if (response.code() == 302) {
                val cookieList = response.headers().values("Set-Cookie")
                val cookieId = cookieList[0].split(";").toTypedArray()[0]
                val cookieName = cookieId.split("=").toTypedArray()[0]
                if (cookieName.equals("sysauth")) {
                    emit(Resource.success(data = authenticateRepository.authenticate(username, password)))
                    return@liveData
                }
            }

            emit(Resource.error(data = null, message = "Forbidden" ?: "Error Occurred!"))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

//    private val _result = MutableLiveData<Resource<String>>()
//    val result: LiveData<Resource<String>> = _result
//
//
//    fun get(username: String, password: String) = viewModelScope.launch {
//        try {
//            val response = authenticateRepository.authenticate(username, password)
//            if (response.code() == 302) {
//                val cookieList = response.headers().values("Set-Cookie")
//                val cookieId = cookieList[0].split(";").toTypedArray()[0]
//                val cookieName = cookieId.split("=").toTypedArray()[0]
//                if (cookieName.equals("sysauth"))
//                    _result.value = Resource.success(data = cookieName)
//                return@launch
//            }
//            _result.value = Resource.error(data = null, message = "Forbidden" ?: "Error Occurred!")
//        } catch (exception: Exception) {
//            _result.value =
//                Resource.error(data = null, message = exception.message ?: "Error Occurred!")
//        }
//    }
}
