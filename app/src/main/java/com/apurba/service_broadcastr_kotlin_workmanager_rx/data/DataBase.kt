package com.apurba.service_broadcastr_kotlin_workmanager_rx.data

class DataBase {
    companion object{
        private val db : DataBase = DataBase()
        fun getItemListForBackGroundService(page: Int) : List<ServiceItem> {
            for (i in 1..10000000000){}
            return db.getItemList(page)
        }

        fun getItemList() = db.getItemList(1)
    }

    private fun getItemList(page : Int) : List<ServiceItem>{
        val data : MutableList<ServiceItem> = mutableListOf()
        for (i in 1..20) data.add(ServiceItem(title = "Title No : $i"))
        return data
    }
}