package com.apurba.service_broadcastr_kotlin_workmanager_rx.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apurba.service_broadcastr_kotlin_workmanager_rx.R
import com.apurba.service_broadcastr_kotlin_workmanager_rx.adapter.SimpleLinearAdapter
import com.apurba.service_broadcastr_kotlin_workmanager_rx.data.DataBase
import com.apurba.service_broadcastr_kotlin_workmanager_rx.data.ServiceItem
import com.apurba.service_broadcastr_kotlin_workmanager_rx.service.ItemLoaderBackgroundService

class ServiceControllerActivity : AppCompatActivity() {

    private lateinit var adapter : SimpleLinearAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_controller)

        setRecyclerView()
        loadData()
    }

    private fun setRecyclerView(){
        val rvHome: RecyclerView = findViewById(R.id.rv_service_controller)
        rvHome.setHasFixedSize(true)
        rvHome.setItemViewCacheSize(20)

        val linearLayoutManager = LinearLayoutManager(this)
        rvHome.layoutManager = linearLayoutManager

        adapter = SimpleLinearAdapter(R.layout.service_item)
        rvHome.adapter = adapter

//        val dataSet = DataBase.getItemList(1)
//        adapter.setDataSet(dataSet)

    }

    private fun loadData(){
        Intent(this, ItemLoaderBackgroundService::class.java).also { intent ->
            startService(intent)
        }
    }
}