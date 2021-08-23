package com.apurba.service_broadcastr_kotlin_workmanager_rx.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apurba.service_broadcastr_kotlin_workmanager_rx.LogWriter
import com.apurba.service_broadcastr_kotlin_workmanager_rx.R
import com.apurba.service_broadcastr_kotlin_workmanager_rx.adapter.SimpleLinearAdapter
import com.apurba.service_broadcastr_kotlin_workmanager_rx.data.ServiceItem
import com.apurba.service_broadcastr_kotlin_workmanager_rx.service.ItemLoaderBackgroundService
import com.apurba.service_broadcastr_kotlin_workmanager_rx.service.ItemLoaderBoundService

class ServiceControllerActivity : AppCompatActivity() {

    private lateinit var adapter : SimpleLinearAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_controller)

        setRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        Intent(this, ItemLoaderBoundService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }



    override fun onStop() {
        super.onStop()
        unbindService(connection)
        mBound = false
    }


    private fun setRecyclerView(){
        val rvHome: RecyclerView = findViewById(R.id.rv_service_controller)
        rvHome.setHasFixedSize(true)
        rvHome.setItemViewCacheSize(20)

        val linearLayoutManager = LinearLayoutManager(this)
        rvHome.layoutManager = linearLayoutManager

        adapter = SimpleLinearAdapter(R.layout.service_item)
        rvHome.adapter = adapter
    }

    // background service
    private fun loadDataForBackGround(){
        Intent(this, ItemLoaderBackgroundService::class.java).also { intent ->
            startService(intent)
        }
    }

    // foreground service
    private fun loadData(){
        if (mBound)  mService.loadData(1) else LogWriter.writToLog("AAA", "Service not bounded Yet!!")
    }

    private fun loadSuccess(data : List<ServiceItem>){
        runOnUiThread {
            adapter.clearData()
            adapter.setDataSet(data) }
    }

    fun loadClicked(view : View){
        loadData()
    }







    private lateinit var mService: ItemLoaderBoundService
    private var mBound: Boolean = false

    /** Defines callbacks for service binding, passed to bindService()  */
    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as ItemLoaderBoundService.LocalBinder
            mService = binder.getService()
            mService.dataListener = {loadSuccess(it)}
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }
}