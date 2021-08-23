package com.apurba.service_broadcastr_kotlin_workmanager_rx.service

import android.app.Service
import android.content.Intent
import android.os.*
import com.apurba.service_broadcastr_kotlin_workmanager_rx.LogWriter
import com.apurba.service_broadcastr_kotlin_workmanager_rx.data.DataBase
import com.apurba.service_broadcastr_kotlin_workmanager_rx.data.ServiceItem

class ItemLoaderBoundService : Service(){
    // Binder given to clients
    private val binder = LocalBinder()
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null
    var dataListener : ((List<ServiceItem>) -> Unit)? = null

    fun loadData(startId : Int){
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }
    }

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            try {
                val data : List<ServiceItem> = DataBase.getItemList()
                data.forEach { LogWriter.writToLog("AAA", it.toString()) }
                dataListener?.invoke(data)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
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
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onDestroy() {
        super.onDestroy()
        LogWriter.writToLog("AAA", "--------Service is Destroying--------")
    }


    inner class LocalBinder : Binder() {
        fun getService(): ItemLoaderBoundService = this@ItemLoaderBoundService
    }
}