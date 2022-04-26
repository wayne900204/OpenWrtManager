package com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.repository

import androidx.lifecycle.LiveData
import com.example.openwrtmanager.com.example.openwrtmanager.AppDatabase
import com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.database.IdentityItem

class IdentityItemRepository(
    private val database: AppDatabase
) {
    suspend fun insertIdentityItem(todoItem: IdentityItem) {
        database.identityItemDao().insert(todoItem)
    }

    suspend fun updateIdentityItem(display: String, username: String, password: String, id: Int) {
        database.identityItemDao().update(display, username, password, id)
    }

    fun getIdentityItems(): LiveData<List<IdentityItem>> {
        return database.identityItemDao().findAll()
    }

    suspend fun deleteIdentityItemByID(id: Int) {
        database.identityItemDao().deleteByID(id)
    }
}