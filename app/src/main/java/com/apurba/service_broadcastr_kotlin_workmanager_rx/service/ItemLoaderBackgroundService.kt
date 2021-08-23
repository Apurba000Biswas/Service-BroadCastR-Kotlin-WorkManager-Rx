package com.apurba.service_broadcastr_kotlin_workmanager_rx.service

import android.app.Service
import android.content.Intent
import android.os.*
import com.apurba.service_broadcastr_kotlin_workmanager_rx.LogWriter
import com.apurba.service_broadcastr_kotlin_workmanager_rx.data.DataBase
import com.apurba.service_broadcastr_kotlin_workmanager_rx.data.ServiceItem


class ItemLoaderBackgroundService : Service(){

    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            try {
                val data : List<ServiceItem> = DataBase.getItemList(1)
                data.forEach { LogWriter.writToLog("AAA", it.toString()) }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
            stopSelf(msg.arg1)
        }
    }



    override fun onCreate() {
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        // This is not Bound Service
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        LogWriter.writToLog("AAA", "--------Service is Destroying--------")
    }
}