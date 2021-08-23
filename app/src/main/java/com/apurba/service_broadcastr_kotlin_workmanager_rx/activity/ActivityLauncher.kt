package com.apurba.service_broadcastr_kotlin_workmanager_rx.activity

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class ActivityLauncher {
    companion object {
        fun launchActivity(context: Context ,type : Class<*>){
            val intent = Intent(context, type)
            startActivity(context , intent, null)
        }
    }
}