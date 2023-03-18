package com.zrq.customview.pixel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zrq.customview.databinding.ActivityPixelBinding

class PixelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPixelBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private lateinit var mBinding: ActivityPixelBinding
}