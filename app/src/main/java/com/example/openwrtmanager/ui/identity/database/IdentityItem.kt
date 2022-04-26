package com.example.openwrtmanager.com.example.openwrtmanager.ui.slideshow.database

import androidx.room.*
import java.util.*

@Entity(
    tableName = IdentityItem.TABLE_NAME
)
data class IdentityItem(
    @ColumnInfo(name = COLUMN_UUID) var uuid:String,
    @ColumnInfo(name = COLUMN_DISPLAY_NAME) var displayName:String,
    @ColumnInfo(name = COLUMN_USERNAME) var username:String,
    @ColumnInfo(name = COLUMN_PASSWORD) var password:String,
    @ColumnInfo(name = COLUMN_CREATED_AT) var createdAt: Date
){
    companion object {
        const val TABLE_NAME = "identity_items"

        const val COLUMN_ID = "id"
        const val COLUMN_UUID = "uuid"
        const val COLUMN_DISPLAY_NAME = "display_name"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_CREATED_AT = "created_at"
    }

    // 必須為 var 才會有 setter
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) var id: Int = 0
}