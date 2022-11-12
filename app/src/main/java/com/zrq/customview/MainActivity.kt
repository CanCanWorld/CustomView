package com.zrq.customview

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zrq.customview.databinding.ActivityMainBinding
import com.zrq.customview.view.FloatView
import kotlin.math.sqrt


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
    private fun initData() {
        mBottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_bottom_new, null)
        mBottomSheetDialog.setContentView(view)
    }

    private fun initEvent() {
        mBinding.btnBottom.setOnClickListener {
            mBottomSheetDialog.show()
        }
        mBinding.btnFloatView.setOnClickListener {
            val contentView = this.window.decorView.findViewById(android.R.id.content) as FrameLayout
            contentView.addView(FloatView(this))
        }
        mBinding.btnDragRv.setOnClickListener {
            startActivity(Intent(this, DragRvActivity::class.java))
        }

    }


    private fun getDistance(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        return sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).toDouble()).toInt()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}