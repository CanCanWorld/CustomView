package com.zrq.customview.buxiugang

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.core.ImageCapture
import com.zrq.customview.R
import com.zrq.customview.databinding.ActivityBuXiuGangBinding

class BuXiuGangActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBuXiuGangBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initData()
        initEvent()
    }


    private fun initData() {
        val imageCapture = ImageCapture.Builder()
            .build()
    }

    private fun initEvent() {

    }

    private fun createCameraPreview(){
    }

    private lateinit var mBinding: ActivityBuXiuGangBinding

    private fun checkCamera() = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
}