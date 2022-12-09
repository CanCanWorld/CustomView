package com.zrq.customview.demo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.zrq.customview.R
import com.zrq.customview.databinding.ActivityDemoBinding

class DemoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setScreen()
        initData()
        initEvent()
    }

    private lateinit var mBinding: ActivityDemoBinding

    private fun initData() {

    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun initEvent() {
        mBinding.apply {
            backgroundView.setOnClickListener {
                setScreen()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun setScreen() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        val lp: WindowManager.LayoutParams = window.attributes
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        window.attributes = lp
    }
}