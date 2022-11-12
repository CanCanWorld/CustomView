package com.zrq.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.zrq.customview.databinding.ActivityDragRvBinding
import kotlin.collections.ArrayList

class DragRvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDragRvBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initData()
        initEvent()
    }

    private lateinit var mBinding: ActivityDragRvBinding
    private lateinit var mAdapter: DragAdapter
    private val list = ArrayList<String>()

    private fun initData() {

        for (i in 1..20) {
            list.add(i.toString())
        }

        list.shuffle()

        mAdapter = DragAdapter(this, list)
        mBinding.recyclerView.adapter = mAdapter
        mBinding.recyclerView.layoutManager = GridLayoutManager(this, 4)
        val dragCallBack = DragCallBack(mAdapter, list)
        val itemTouchHelper = ItemTouchHelper(dragCallBack)
        itemTouchHelper.attachToRecyclerView(mBinding.recyclerView)
    }

    private fun initEvent() {

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}