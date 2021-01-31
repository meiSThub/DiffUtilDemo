package com.mei.diffutil.demo.diff

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mei.diffutil.demo.User
import com.mei.diffutil.demo.async.AsyncListDifferAdapter
import com.mei.diffutil.demo.databinding.ItemDiffUtilBinding

/**
 * @Author: mxb
 * @Description:
 * @Date: 2021/1/29
 */
class DiffUtilAdapter : RecyclerView.Adapter<DiffUtilAdapter.ViewHolder>() {

    companion object {
        private const val TAG = "DiffUtilAdapter"
    }

    private var dataList: List<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = ItemDiffUtilBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, binding.root)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var user = getItem(position)
        holder.binding.tvName.text = user?.name
        holder.binding.tvOrder.text = user?.age.toString()

        Log.i(TAG, "onBindViewHolder: position=$position")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            // 如果当前item的内容没有更新，即刷新RecyclerView的时候，当前item内容没有变或者内容是全新的，则payloads返回的内容是空的
            super.onBindViewHolder(holder, position, payloads)
            return
        }

        Log.i(TAG, "onBindViewHolder: 更新的item=$position")
        var bundle: Bundle = payloads[0] as Bundle

        var name = bundle.getString("name")
        var age = bundle.getInt("age")
        if (!TextUtils.isEmpty(name)) {
            holder.binding.tvName.text = name
        }

        if (age >= 0) {
            holder.binding.tvOrder.text = age.toString()
        }
    }

    private fun getItem(position: Int): User? {
        if (dataList == null || position < 0 || position >= itemCount) {
            return null
        }
        return dataList?.get(position)
    }

    fun setDataList(list: List<User>) {
        val calculateDiff = DiffUtil.calculateDiff(DiffUtilCallback(dataList, list))
        calculateDiff.dispatchUpdatesTo(this)
        dataList = list
    }

    fun getDataList(): List<User>? {
        return dataList
    }

    class ViewHolder(var binding: ItemDiffUtilBinding, itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }
}
