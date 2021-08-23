package com.apurba.service_broadcastr_kotlin_workmanager_rx.adapter

import com.apurba.service_broadcastr_kotlin_workmanager_rx.R

class SimpleLinearAdapter(private val layoutId : Int) : BaseAdapter() {
    /*
     * Created by APURBA BISWAS
     */
    companion object{
        const val ITEM_VIEW_TYPE = 0
        const val LOADING_VIEW_TYPE = 1
    }


    private var paginationActive = false
    private var dataSet : MutableList<Any> ? = null
    private var clickListener : Any? = null
    private var positionalClickListener: PositionalClickListener? = null

    fun setDataSet(dataSet : List<Any>?){
        if (this.dataSet == null) this.dataSet = ArrayList()
        if (dataSet == null){
            setPaginationActive(false);
            super.notifyAdapter(true)
        }else{
            this.dataSet!!.addAll(dataSet)
            super.notifyAdapter(false)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == dataSet?.size) LOADING_VIEW_TYPE
        else ITEM_VIEW_TYPE
    }

    fun clearData(){
        this.dataSet?.clear()
        notifyDataSetChanged()
    }

    fun setPaginationActive(flag : Boolean){
        this.paginationActive = flag
    }


    fun setClickListener(clickListener : Any?){
        this.clickListener = clickListener
    }

    override fun getItemCount(): Int {
        return if (dataSet == null) 0
        else{
            if (paginationActive) dataSet!!.size + 1
            else dataSet!!.size
        }
    }

    override fun getDataAtPosition(position: Int): Any? {
        if (getItemViewType(position) == LOADING_VIEW_TYPE) return null
        return dataSet!![position]
    }

    override fun getLayoutIdForType(viewType: Int): Int {
        return if (viewType == LOADING_VIEW_TYPE) {
            R.layout.pagination_default_footer
        } else layoutId
    }

    override fun getClickListener(position: Int, viewType: Int): Any? {
        return clickListener
    }


    interface PositionalClickListener{
        fun onPositionalItemClick(currentPosition: Int, previousPosition: Int ,data : Any?)
    }
}