package com.example.openwrtmanager.com.example.openwrtmanager.ui.device.database

import androidx.room.*
import java.util.*

@Entity(
    tableName = DeviceItem.TABLE_NAME
)
data class DeviceItem(
    @ColumnInfo(name = COLUMN_UUID) var uuid:String,
    @ColumnInfo(name = COLUMN_DISPLAY_NAME) var displayName:String,
    @ColumnInfo(name = COLUMN_ADDRESS) var address:String,
    @ColumnInfo(name = COLUMN_PORT) var port:String,
    @ColumnInfo(name = COLUMN_IDENTITY_UUID) var identityUuid:String,
    @ColumnInfo(name = COLUMN_USE_HTTPS_CONNECTION) var useHttpsConnection:Boolean,
    @ColumnInfo(name = COLUMN_IGNORE_BAD_CERTIFICATE) var ignoreBadCertificate:Boolean,
    @ColumnInfo(name = COLUMN_CREATED_AT) var createdAt: Date
){
    companion object {
        const val TABLE_NAME = "device_items"

        const val COLUMN_ID = "id"
        const val COLUMN_UUID = "uuid"
        const val COLUMN_DISPLAY_NAME = "display_name"
        const val COLUMN_ADDRESS = "address"
        const val COLUMN_PORT = "port"
        const val COLUMN_IDENTITY_UUID = "identity_uuid"
        const val COLUMN_USE_HTTPS_CONNECTION = "use_https_connection"
        const val COLUMN_IGNORE_BAD_CERTIFICATE = "ignore_bad_certificate"
        const val COLUMN_CREATED_AT = "created_at"
    }

    // 必須為 var 才會有 setter
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) var id: Int = 0
}