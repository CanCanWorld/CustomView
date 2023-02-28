package com.zrq.customview.switcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zrq.customview.databinding.ActivitySwitcherBinding

class SwitcherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySwitcherBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.switcher.setOnCheckedChangeListener {
            Log.d("TAG", "onCreate: $it")
        }
    }

    private lateinit var mBinding: ActivitySwitcherBinding

}