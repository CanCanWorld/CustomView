package com.zrq.customview.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zrq.customview.R
import com.zrq.customview.databinding.ActivityStudyBinding

class StudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityStudyBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private lateinit var mBinding: ActivityStudyBinding

}