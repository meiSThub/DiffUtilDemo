package com.mei.diffutil.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mei.diffutil.demo.async.AsyncListDifferActivity
import com.mei.diffutil.demo.diff.DiffUtilActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun diffUtil(view: View) {
        startActivity(Intent(this, DiffUtilActivity::class.java))
    }

    fun asyncListDiffer(view: View) {
        startActivity(Intent(this, AsyncListDifferActivity::class.java))
    }
}
