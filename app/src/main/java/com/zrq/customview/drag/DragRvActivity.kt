package com.zrq.customview.drag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.zrq.customview.OnItemClickListener
import com.zrq.customview.databinding.ActivityDragRvBinding
import kotlin.collections.ArrayList

class DragRvActivity : AppCompatActivity(), OnItemClickListener {
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

        for (i in 1..200) {
            list.add(i.toString())
        }

        mAdapter = DragAdapter(this, list, this)
        mBinding.recyclerView.adapter = mAdapter
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        val dragCallBack = DragCallBack(mAdapter, list)
        val itemTouchHelper = ItemTouchHelper(dragCallBack)
        itemTouchHelper.attachToRecyclerView(mBinding.recyclerView)
    }

    private fun initEvent() {
        mBinding.apply {
            pullToRefresh.setOnRefreshListener {
                Thread {
                    Thread.sleep(2000)
                    runOnUiThread{
                        pullToRefresh.isRefreshing = false
                    }
                }.start()
            }
        }
    }

    override fun onItemClick(view: View, position: Int) {
        Toast.makeText(this, "click:$position->${list[position]}", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}