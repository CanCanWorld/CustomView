package com.zrq.customview.drag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.zrq.customview.OnItemClickListener
import com.zrq.customview.databinding.ActivityDragRvBinding
import com.zrq.customview.drag2.Drag2Adapter
import com.zrq.customview.drag2.ItemTouchHelperCallback
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
//    private lateinit var mAdapter: Drag2Adapter
    private val list = ArrayList<String>()

    private fun initData() {

        for (i in 1..200) {
            list.add(i.toString())
        }

        mAdapter = DragAdapter(this, list, this)
//        mAdapter = Drag2Adapter(this, list, this)
        mBinding.recyclerView.adapter = mAdapter
        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        val dragCallBack = DragCallBack(mAdapter, list)
//        val dragCallBack = ItemTouchHelperCallback(mAdapter)
        val itemTouchHelper = ItemTouchHelper(dragCallBack)
        itemTouchHelper.attachToRecyclerView(mBinding.recyclerView)
    }

    private fun initEvent() {

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