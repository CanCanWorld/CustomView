package com.zrq.customview

import android.util.Log
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class DragCallBack(
    private val adapter: DragAdapter,
    private val data: MutableList<String>
) : ItemTouchHelper.Callback() {

    private var mRecyclerView: RecyclerView? = null

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        mRecyclerView = recyclerView
        var dragFlags = 0
        var swipeFlags = 0
        return when (recyclerView.layoutManager) {
            is GridLayoutManager -> {
                dragFlags = ItemTouchHelper.LEFT or ItemTouchHelper.UP or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN
                makeMovementFlags(dragFlags, swipeFlags)
            }
            is LinearLayoutManager -> {
                dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                makeMovementFlags(dragFlags, swipeFlags)
            }
            else -> {
                0
            }
        }
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition

        if (fromPosition < toPosition) {
            for (index in fromPosition until toPosition) {
                Collections.swap(data, index, index + 1)
            }
        } else {
            for (index in fromPosition downTo toPosition + 1) {
                Collections.swap(data, index, index - 1)
            }
        }
        adapter.notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.START) {
            Log.i(TAG, "-->")
        } else {
            Log.i(TAG, "<--")
        }
        val position = viewHolder.adapterPosition
        data.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when (actionState) {
            ItemTouchHelper.ACTION_STATE_IDLE -> {
            }
            else -> {
                viewHolder?.let {
                    ViewCompat.animate(it.itemView).setDuration(200).scaleX(1.2f).scaleY(1.2f).start()
                }
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        ViewCompat.animate(viewHolder.itemView).setDuration(200).scaleX(1f).scaleY(1f).start()
    }

    companion object {
        const val TAG = "DragCallBack"
    }

}