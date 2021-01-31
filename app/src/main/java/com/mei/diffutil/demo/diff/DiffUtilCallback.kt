package com.mei.diffutil.demo.diff

import android.os.Bundle
import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil
import com.mei.diffutil.demo.User

/**
 * @Author: mxb
 * @Description:
 * @Date: 2021/1/31
 */
class DiffUtilCallback(var oldList: List<User>?, var newList: List<User>?) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList?.size ?: 0
    }

    private fun getItem(list: List<User>?, position: Int): User? {
        if (list == null || position < 0 || position >= list.size) {
            return null
        }
        return list[position]
    }

    /**
     * 是否是同一个item
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        var oldItem = getItem(oldList, oldItemPosition)
        var newItem = getItem(newList, newItemPosition)
        if (oldItem == null || newItem == null) {
            return false
        }
        return oldItem.id == newItem.id
    }

    /**
     * item的内容是否相同
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        var oldItem = getItem(oldList, oldItemPosition)
        var newItem = getItem(newList, newItemPosition)
        if (oldItem == null || newItem == null) {
            return false
        }
        return TextUtils.equals(oldItem.name, newItem.name) && oldItem.age == newItem.age
    }

    /**
     * 获取item更新的内容
     */
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        var changeInfo = Bundle()
        var oldItem = getItem(oldList, oldItemPosition)
        var newItem = getItem(newList, newItemPosition)
        if (oldItem == null || newItem == null) {
            return false
        }
        if (!TextUtils.equals(oldItem.name, newItem.name)) {
            changeInfo.putString("name", newItem.name)
        }

        if (oldItem.age != newItem.age) {
            changeInfo.putInt("age", newItem.age)
        }
//        if (changeInfo.size() <= 0) {
//            return null
//        }
        return changeInfo
    }
}
