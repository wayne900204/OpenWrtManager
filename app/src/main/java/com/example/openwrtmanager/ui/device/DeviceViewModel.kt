package com.example.openwrtmanager.ui.device

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database.DeviceItem
import com.example.openwrtmanager.com.example.openwrtmanager.ui.device.repository.DeviceItemRepository
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.database.IdentityItem
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.repository.IdentityItemRepository
import kotlinx.coroutines.launch
import java.util.*

class DeviceViewModel(private val repository: DeviceItemRepository,private val identityItemRepository: IdentityItemRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
    val todoLiveData: LiveData<List<DeviceItem>> = repository.getDeviceItems()
    val identityItemsLiveData: LiveData<List<IdentityItem>> = identityItemRepository.getIdentityItems()
    fun createNewTodo(deviceItem: DeviceItem) {

        viewModelScope.launch {
            repository.insertDeviceItem(deviceItem)
        }
    }

    fun deleteIdentityItemByID(id: Int) {
        viewModelScope.launch {
            repository.deleteDeviceItemByID(id)
        }
    }
}