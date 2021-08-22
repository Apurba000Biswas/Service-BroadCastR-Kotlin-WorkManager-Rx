package com.apurba.service_broadcastr_kotlin_workmanager_rx.activity

import android.os.Bundle
import android.view.View
import com.apurba.service_broadcastr_kotlin_workmanager_rx.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onServiceClicked(view: View) {
        ActivityLauncher.launchActivity(this , ServiceControllerActivity::class.java)
    }
}