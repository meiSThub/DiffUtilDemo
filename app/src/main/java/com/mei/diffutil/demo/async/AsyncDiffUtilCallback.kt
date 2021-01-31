package com.mei.diffutil.demo.async

import android.os.Bundle
import android.text.TextUtils
import androidx.recyclerview.widget.DiffUtil
import com.mei.diffutil.demo.User

/**
 * @Author: mxb
 * @Description:
 * @Date: 2021/1/31
 */
class AsyncDiffUtilCallback : DiffUtil.ItemCallback<User>() {

    /**
     * 是否是同一个item
     */
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * item 的内容是否相同
     * @return true:内容相同，false：内容不相同，即内容有更新，需要刷新这个item对应的UI
     */
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        if (!TextUtils.equals(oldItem.name, newItem.name)) {
            return false
        }

        if (oldItem.age != newItem.age) {
            return false
        }

        return true
    }

    /**
     * 如果item内容有更新，则取出更新的部分，封装成一个对象返回，这样在刷新RecyclerView的时候，就只会刷新更改的部分，
     * 不会对item没有变的部分也进行更新，节省了时间
     *
     * 返回的对象，会通过RecyclerView#Adapter的onBindViewHolder(VH holder, int position,List<Object> payloads)
     * 方法的第三个参数传递给用户进行数据绑定
     */
    override fun getChangePayload(oldItem: User, newItem: User): Any? {
        var changeInfo = Bundle()
        if (!TextUtils.equals(oldItem.name, newItem.name)) {
            changeInfo.putString("name", newItem.name)
        }

        if (oldItem.age != newItem.age) {
            changeInfo.putInt("age", newItem.age)
        }
        if (changeInfo.size() <= 0)
            return null
        return changeInfo
    }

}
