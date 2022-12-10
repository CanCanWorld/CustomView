package com.zrq.customview.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zrq.customview.R
import com.zrq.customview.databinding.ActivityDemo2Binding

class Demo2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDemo2Binding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private lateinit var mBinding : ActivityDemo2Binding
}