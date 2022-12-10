package com.zrq.customview.progress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zrq.customview.R
import com.zrq.customview.databinding.ActivityProgressBinding

class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initEvent()
    }

    private lateinit var mBinding: ActivityProgressBinding

    private fun initEvent() {
//        Thread {
//            while (mBinding.seekBar.progress < 100) {
//                mBinding.seekBar.progress++
//                Log.d("TAG", "initEvent: ${mBinding.seekBar.progress}")
//                Thread.sleep(200)
//            }
//        }.start()
    }
}