package com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.openwrtmanager.utils.GenericDao
@Dao
interface IdentityItemDao : GenericDao<IdentityItem> {

    @Query("SELECT * FROM ${IdentityItem.TABLE_NAME} ORDER BY ${IdentityItem.COLUMN_CREATED_AT} DESC")
    fun findAll(): LiveData<List<IdentityItem>>

    @Query("DELETE FROM ${IdentityItem.TABLE_NAME} WHERE ${IdentityItem.COLUMN_ID} LIKE :id")
    suspend fun deleteByID(id: Int)

    @Query("UPDATE  ${IdentityItem.TABLE_NAME} SET ${IdentityItem.COLUMN_DISPLAY_NAME} = :display, ${IdentityItem.COLUMN_USERNAME} = :username, ${IdentityItem.COLUMN_PASSWORD} =:password WHERE ${IdentityItem.COLUMN_ID} LIKE :id")
    suspend fun update(display: String, username: String, password: String, id: Int)
}