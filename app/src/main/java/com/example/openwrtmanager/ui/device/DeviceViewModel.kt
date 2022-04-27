package com.example.openwrtmanager.ui.device

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database.DeviceItem
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository.DeviceItemRepository
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.database.IdentityItem
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.repository.IdentityItemRepository
import com.example.openwrtmanager.ui.identity.IdentityFragment
import kotlinx.coroutines.launch

class DeviceViewModel(
    private val repository: DeviceItemRepository,
    private val identityItemRepository: IdentityItemRepository
) : ViewModel() {

    private val TAG = DeviceViewModel::class.qualifiedName
    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
    val todoLiveData: LiveData<List<DeviceItem>> = repository.getDeviceItems()
    val identityItemsLiveData: LiveData<List<IdentityItem>> =
        identityItemRepository.getIdentityItems()

    fun createDeviceItem(deviceItem: DeviceItem) {

        viewModelScope.launch {
            repository.insertDeviceItem(deviceItem)
        }
    }

    fun deleteDeviceItemByID(id: Int) {
        viewModelScope.launch {
            repository.deleteDeviceItemById(id)
        }
    }

    fun getDeviceItemByID(id: Int): LiveData<DeviceItem> {
        return repository.getDeviceItemById(id)
    }

    fun updateDeviceItemById(
        display: String,
        address: String,
        port: String,
        identityUuid: String,
        useHttpsConnection: Boolean,
        ignoreBadCertificate: Boolean,
        id: Int
    ) {
        viewModelScope.launch {
            repository.updateDeviceItemById(
                display,
                address,
                port,
                identityUuid,
                useHttpsConnection,
                ignoreBadCertificate,
                id
            )
        }
    }
}
