package com.zrq.customview.swipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zrq.customview.databinding.ActivitySwipeBinding

class SwipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySwipeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initData()
    }

    private lateinit var mBinding: ActivitySwipeBinding
    private lateinit var adapter: SwipeAdapter
    private val list = mutableListOf<String>()

    private fun initData() {
        for (i in 1..10) {
            list.add("第 $i 个fragment")
        }
        adapter = SwipeAdapter(supportFragmentManager, list)
        mBinding.apply {
            swipeViewPager.adapter = adapter
        }
    }

}