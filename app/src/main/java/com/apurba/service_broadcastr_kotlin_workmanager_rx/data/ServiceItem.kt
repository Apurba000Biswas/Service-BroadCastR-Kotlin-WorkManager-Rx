package com.apurba.service_broadcastr_kotlin_workmanager_rx.data

data class ServiceItem(val title : String = "Default Title", val subTitle : String =" This is just a demo sub title"){
    override fun toString(): String {
        return "ServiceItem(title='$title', subTitle='$subTitle')"
    }
}
