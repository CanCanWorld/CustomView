package com.zrq.customview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zrq.customview.databinding.ActivityMainBinding
import com.zrq.customview.demo.Demo2Activity
import com.zrq.customview.demo.DemoActivity
import com.zrq.customview.drag.DragRvActivity
import com.zrq.customview.float.FloatView
import com.zrq.customview.img.ImageActivity
import com.zrq.customview.snow.SnowActivity


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initData()
        initEvent()
    }

    private lateinit var mBottomSheetDialog: BottomSheetDialog

    @SuppressLint("InflateParams")
    private fun initData() {
        mBottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_bottom_new, null)
        mBottomSheetDialog.setContentView(view)
    }

    private fun initEvent() {
        mBinding.apply {

            btnBottom.setOnClickListener {
                mBottomSheetDialog.show()
            }
            btnFloatView.setOnClickListener {
                val contentView = this@MainActivity.window.decorView.findViewById(android.R.id.content) as FrameLayout
                contentView.addView(FloatView(this@MainActivity))
            }
            btnDragRv.setOnClickListener {
                startActivity(Intent(this@MainActivity, DragRvActivity::class.java))
            }
            btnSnow.setOnClickListener {
                startActivity(Intent(this@MainActivity, SnowActivity::class.java))
            }
            btnImg.setOnClickListener {
                startActivity(Intent(this@MainActivity, ImageActivity::class.java))
            }
            btnDemo.setOnClickListener {
                startActivity(Intent(this@MainActivity, DemoActivity::class.java))
            }
            btnDemo2.setOnClickListener {
                startActivity(Intent(this@MainActivity, Demo2Activity::class.java))
            }
        }

    }

    companion object {
        const val TAG = "MainActivity"
    }
}