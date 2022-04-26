package com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database



import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.openwrtmanager.utils.GenericDao
@Dao
interface DeviceItemDao : GenericDao<DeviceItem> {

    @Query("SELECT * FROM ${DeviceItem.TABLE_NAME} ORDER BY ${DeviceItem.COLUMN_CREATED_AT} DESC")
    fun findAll(): LiveData<List<DeviceItem>>

    @Query("DELETE FROM ${DeviceItem.TABLE_NAME} WHERE ${DeviceItem.COLUMN_ID} LIKE :id")
    suspend fun deleteByID(id: Int)
}