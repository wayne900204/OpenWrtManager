package com.example.openwrtmanager

import android.content.Context
import android.content.pm.ApplicationInfo
import java.nio.file.Path

class SettingsUtil (context: Context){
    fun localPath():String{
//            return context.applicationInfo.dataDir
        return MainApplication.applicationContext().applicationInfo.dataDir
    }
    companion object {
//        fun localPath(context: Context):String{

    }

}