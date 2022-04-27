package com.example.openwrtmanager.ui.identity

import androidx.lifecycle.*
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.database.IdentityItem
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.repository.IdentityItemRepository
import kotlinx.coroutines.launch
import java.util.*

class IdentityViewModel(private val repository: IdentityItemRepository) : ViewModel() {

    private val TAG = IdentityViewModel::class.qualifiedName
    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text
    val todoLiveData: LiveData<List<IdentityItem>> = repository.getIdentityItems()

    fun createNewTodo(display: String, username: String, password: String) {
        val todoItem = IdentityItem(
            createdAt = Date(),
            uuid = UUID.randomUUID().toString(),
            displayName = display,
            username = username,
            password = password,

            )
        viewModelScope.launch {
            repository.insertIdentityItem(todoItem)
        }
    }

    fun updateTodo(display: String, username: String, password: String, id: Int) {
        viewModelScope.launch {
            repository.updateIdentityItem(display, username, password, id)
        }
    }

    fun deleteIdentityItemByID(id: Int) {
        viewModelScope.launch {
            repository.deleteIdentityItemByID(id)
        }
    }

}