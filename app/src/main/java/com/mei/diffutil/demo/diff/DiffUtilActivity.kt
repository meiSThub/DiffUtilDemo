package com.mei.diffutil.demo.diff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mei.diffutil.demo.User
import com.mei.diffutil.demo.UserFactory
import com.mei.diffutil.demo.databinding.ActivityDiffUtilBinding

/**
 * @Author: mxb
 * @Description:
 * @Date: 2021/1/29
 */
class DiffUtilActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityDiffUtilBinding
    private lateinit var mAdapter: DiffUtilAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDiffUtilBinding.inflate(LayoutInflater.from(this))
        setContentView(mBinding.root)

        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        mAdapter = DiffUtilAdapter()
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
