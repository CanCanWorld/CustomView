package com.zrq.customview.flow

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.zrq.customview.R
import com.zrq.customview.databinding.ActivityFlowBinding
import com.zrq.customview.databinding.ItemFlowBinding

class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initData()
    }

    private lateinit var mBinding: ActivityFlowBinding
    private val list = mutableListOf<String>()

    @SuppressLint("InflateParams")
    private fun initData() {
        mBinding.apply {
            list.add("Java")
            list.add("Android")
            list.add("JavaEE")
            list.add("c#")
            list.add("c")
            list.add("c++")
            list.add("Flutter")
            list.add("Vue")
            list.add("Html")
            list.add("Jsp")
            list.add("MySQL")
            list.add("Kotlin")
            list.add("哼哼啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊")
            for (i in 0..20) {
                list.add(i.toString())
            }
            list.forEach {
                val view = LayoutInflater.from(this@FlowActivity).inflate(R.layout.item_flow, null)
                view.tag = it
                view.findViewById<TextView>(R.id.tv_title).text = it
                flowLayout.addView(view)
            }
        }
    }
}