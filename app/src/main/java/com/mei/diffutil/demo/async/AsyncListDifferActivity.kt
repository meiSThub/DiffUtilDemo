package com.mei.diffutil.demo.async

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mei.diffutil.demo.User
import com.mei.diffutil.demo.UserFactory
import com.mei.diffutil.demo.databinding.ActivityAsyncListDifferBinding

/**
 * @Author: mxb
 * @Description:
 * @Date: 2021/1/29
 */
class AsyncListDifferActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityAsyncListDifferBinding
    private lateinit var mAdapter: AsyncListDifferAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAsyncListDifferBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter = AsyncListDifferAdapter()
        mBinding.recyclerView.adapter = mAdapter

        initData()
    }

    private fun initData() {
        var list = UserFactory.createUserList()
        mAdapter.setDataList(list)
    }

    fun addData(view: View) {
        var list = UserFactory.updateUserList()
        mAdapter.setDataList(list)
    }
}
