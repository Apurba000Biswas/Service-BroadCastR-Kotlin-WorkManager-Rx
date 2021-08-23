package com.apurba.service_broadcastr_kotlin_workmanager_rx.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apurba.service_broadcastr_kotlin_workmanager_rx.R
import com.apurba.service_broadcastr_kotlin_workmanager_rx.adapter.SimpleLinearAdapter
import com.apurba.service_broadcastr_kotlin_workmanager_rx.data.ServiceItem

class ServiceControllerActivity : AppCompatActivity() {

    private lateinit var adapter : SimpleLinearAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_controller)

        setRecyclerView()
    }

    private fun setRecyclerView(){
        val rvHome: RecyclerView = findViewById(R.id.rv_service_controller)
        rvHome.setHasFixedSize(true)
        rvHome.setItemViewCacheSize(20)

        val linearLayoutManager = LinearLayoutManager(this)
        rvHome.layoutManager = linearLayoutManager

        adapter = SimpleLinearAdapter(R.layout.service_item)
        rvHome.adapter = adapter


        val dataSet = listOf(ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem()
            ,ServiceItem())
        adapter.setDataSet(dataSet)

    }
}