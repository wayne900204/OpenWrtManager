package com.example.openwrtmanager.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.model.InfoRequestModel
import com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.repository.InfoRepository
import com.example.openwrtmanager.com.example.openwrtmanager.ui.identity.repository.interval
import com.example.openwrtmanager.com.example.openwrtmanager.ui.information.model.InfoResponseModelItem
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface LCE<out T> {
    data class Content<out T>(val content: T) : LCE<T>
    data class Error(val throwable: Throwable) : LCE<Nothing>
    object Loading : LCE<Nothing>
}

class IdentityViewModel(private var authenticateRepository: InfoRepository,cookie: String) : ViewModel() {
    private val TAG = IdentityViewModel::class.qualifiedName


    private val _startOrStopFlow = MutableSharedFlow<Boolean>(extraBufferCapacity = 1)
    private val _lceLiveData = MutableLiveData<LCE<List<InfoResponseModelItem>>>(LCE.Loading)

    val lceLiveData: LiveData<LCE<List<InfoResponseModelItem>>> get() = _lceLiveData


    init {
        val rrrrr = listOf(
            InfoRequestModel(
                id = 1,
                params = listOf<Any>(
                    cookie, "system", "info", JsonObject()
                )
            ),
            InfoRequestModel(
                id = 2,
                params = listOf<Any>(
                    cookie, "system", "board", JsonObject()
                )
            ),
            InfoRequestModel(
                id = 3,
                params = listOf<Any>(cookie, "luci-rpc", "getDHCPLeases", JsonObject())
            ),
            InfoRequestModel(
                id = 4,
                params = listOf<Any>(
                    cookie, "luci-rpc", "getNetworkDevices", JsonObject()
                )
            ),
            InfoRequestModel(
                id = 5,
                params = listOf<Any>(
                    cookie, "network.interface", "dump", JsonObject()
                )
            ),
        )
        val intervalFlow = interval(initialDelayMillis = 0, periodMillis = 5_000)
            .flatMapLatest {
                flow<LCE<List<InfoResponseModelItem>>> {
                    emit(LCE.Content(authenticateRepository.getInformation(rrrrr)))
                }
                    .onStart { emit(LCE.Loading) }
                    .catch { emit(LCE.Error(it)) }
            }

        _startOrStopFlow.distinctUntilChanged()
            .flatMapLatest { start ->
                if (start) intervalFlow
                else emptyFlow()
            }
            .onEach { _lceLiveData.value = it }
            .launchIn(viewModelScope)
    }

    fun start() {
        viewModelScope.launch { _startOrStopFlow.emit(true) }
    }

    fun stop() {
        viewModelScope.launch { _startOrStopFlow.emit(false) }
    }


//    fun getInformation(cookie: String) {
//        val rrrrr = listOf(
//            ResquestJSONElement(
//                id = 1,
//                params = listOf<Any>(
//                    cookie, "system", "info", ParamClass
//                )
//            ),
//            ResquestJSONElement(
//                id = 2,
//                params = listOf<Any>(
//                    cookie, "system", "board", ParamClass()
//                )
//            ),
//            ResquestJSONElement(
//                id = 3,
//                params = listOf<Any>(cookie, "luci-rpc", "getDHCPLeases", ParamClass())
//            ),
//            ResquestJSONElement(
//                id = 4,
//                params = listOf<Any>(
//                    cookie, "luci-rpc", "getNetworkDevices", ParamClass()
//                )
//            ),
//            ResquestJSONElement(
//                id = 1,
//                params = listOf<Any>(
//                    cookie, "network.interface", "dump", ParamClass()
//                )
//            ),
//        )
//        Log.d(TAG, "getInformation: "+rrrrr)
////            val response = infoRepository.getInformation(rrrrr)
//
//        val intervalFlow = interval(initialDelayMillis = 0, periodMillis = 5_000)
//            .flatMapLatest {
//                flow<LCE<ResponseBody>> {
//                    Log.d(TAG, "getInformationflow: "+authenticateRepository.getInformation(rrrrr))
//                    emit(
//                        LCE.Content(
//                            authenticateRepository.getInformation(
//                                rrrrr
//                            ).body()!!
//                        )
//                    )
//                }
//                    .onStart { emit(LCE.Loading) }
//                    .catch { emit(LCE.Error(it)) }
//            }
//
//        _startOrStopFlow.distinctUntilChanged()
//            .flatMapLatest { start ->
//                if (start) intervalFlow
//                else emptyFlow()
//            }
//            .onEach { _lceLiveData.value = it }
//            .launchIn(viewModelScope)
//    }

}