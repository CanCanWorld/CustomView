package com.zrq.customview

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zrq.customview.databinding.ActivityMainBinding
import com.zrq.customview.databinding.DialogOpenTheDoorBinding
import com.zrq.customview.demo.Demo2Activity
import com.zrq.customview.demo.DemoActivity
import com.zrq.customview.drag.DragRvActivity
import com.zrq.customview.flow.FlowActivity
import com.zrq.customview.snow.SnowActivity
import com.zrq.customview.swipe.SwipeActivity
import com.zrq.customview.switcher.SwitcherActivity


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initData()
        initEvent()
    }

    private lateinit var mBottomSheetDialog: BottomSheetDialog

    private var dialogOpen: AlertDialog? = null
    private val dialogOpenTheDoorBinding by lazy {
        DialogOpenTheDoorBinding.inflate(LayoutInflater.from(this))
    }

    @SuppressLint("InflateParams")
    private fun initData() {
        mBottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_bottom_new, null)
        mBottomSheetDialog.setContentView(view)
    }

    private fun initEvent() {
        val that = this
        mBinding.apply {

            btnBottom.setOnClickListener {
                mBottomSheetDialog.show()
            }
            btnDragRv.setOnClickListener {
                startActivity(Intent(that, DragRvActivity::class.java))
            }
            btnSnow.setOnClickListener {
                startActivity(Intent(that, SnowActivity::class.java))
            }
            btnDemo.setOnClickListener {
                startActivity(Intent(that, DemoActivity::class.java))
            }
            btnDemo2.setOnClickListener {
                startActivity(Intent(that, Demo2Activity::class.java))
            }
            btnFlow.setOnClickListener {
                startActivity(Intent(that, FlowActivity::class.java))
            }
            btnSwipe.setOnClickListener {
                startActivity(Intent(that, SwipeActivity::class.java))
            }
            btnSwitcher.setOnClickListener {
                startActivity(Intent(that, SwitcherActivity::class.java))
            }
            btnDialog.setOnClickListener {
                if (dialogOpen == null) {

                    dialogOpen = AlertDialog.Builder(that, R.style.NormalDialogStyle)
                        .setView(dialogOpenTheDoorBinding.root)
                        .setCancelable(true)
                        .create()
                    dialogOpenTheDoorBinding.apply {
                        btnOpen.setOnClickListener {
                            Log.d(TAG, "initEvent: 开门")
                            tvOpen.text = "正在开门中"

                            val anim = AnimationUtils.loadAnimation(that, R.anim.loading_animation)
                            ivLoading.startAnimation(anim)
                            ivLoading.visibility = View.VISIBLE
                            Thread {
                                Thread.sleep(2000)
                                runOnUiThread {
                                    tvOpen.text = "开门成功, 请取货"
                                    ivLoading.clearAnimation()
                                    ivLoading.visibility = View.GONE
                                }
                            }.start()
                        }
                    }
                }
                dialogOpen?.let {
                    if (!it.isShowing) {
                        it.show()
                    }
                }
            }
        }

    }

    companion object {
        const val TAG = "MainActivity"
    }
}