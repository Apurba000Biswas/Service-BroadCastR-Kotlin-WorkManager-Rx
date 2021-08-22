package com.apurba.service_broadcastr_kotlin_workmanager_rx

import android.util.Log

class LogWriter {
    companion object{
        private const val isDebugMode = true

        fun writToLog(tag : String, message : String){
            if (!isDebugMode) return
            Log.d(tag, message)
        }
    }

}