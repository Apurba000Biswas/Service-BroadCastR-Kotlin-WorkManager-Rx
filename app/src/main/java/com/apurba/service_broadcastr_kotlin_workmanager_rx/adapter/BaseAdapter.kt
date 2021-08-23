package com.apurba.service_broadcastr_kotlin_workmanager_rx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /*
     * Created by APURBA BISWAS
     */
    abstract fun getDataAtPosition(position: Int): Any?
    abstract fun getLayoutIdForType(viewType: Int): Int
    abstract fun getClickListener(position: Int, viewType: Int): Any?

    protected open fun notifyAdapter(isLastPage: Boolean) {
//        this.isLoading = false
//        isLastPage = isLastPage
//        if (isLastPage) nextPage = startPage
//        if (paginationFooterView != null) paginationFooterView.setVisibility(View.GONE)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, getLayoutIdForType(viewType), parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseViewHolder).bindView(
            getDataAtPosition(position),
            getClickListener(position, getItemViewType(position))
        )
    }



    private class BaseViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(`object`: Any?, clickListener: Any?) {
            binding.setVariable(BR.item, `object`)
            binding.setVariable(BR.clickListener, clickListener)
            binding.executePendingBindings()
        }
    }
}