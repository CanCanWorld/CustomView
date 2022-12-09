package com.zrq.customview.drag

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zrq.customview.R
import com.zrq.customview.VH
import com.zrq.customview.databinding.ItemDragViewBinding
import java.util.*
import kotlin.math.abs

class DragCallBack(
    private val adapter: DragAdapter,
    private val data: MutableList<String>
) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.START) {
            Log.i(TAG, "<--")
        } else {
            Log.i(TAG, "-->")
        }
        val position = viewHolder.adapterPosition
        val drawable = viewHolder.itemView.background as GradientDrawable
        drawable.color = ContextCompat.getColorStateList(viewHolder.itemView.context, R.color.green)
        data.removeAt(position)
        Log.d(TAG, "onSwiped: $data")
        adapter.notifyItemRemoved(position)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        when (actionState) {
            ItemTouchHelper.ACTION_STATE_IDLE -> {
            }
            else -> {
                viewHolder?.let {
                    ViewCompat.animate(it.itemView).setDuration(200).scaleX(1.1f).scaleY(1.1f).start()
                    val drawable = viewHolder.itemView.background as GradientDrawable
                    drawable.color = ContextCompat.getColorStateList(viewHolder.itemView.context, R.color.green_)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        Log.d(TAG, "clearView: ")
        ViewCompat.animate(viewHolder.itemView).setDuration(200).scaleX(1f).scaleY(1f).start()
        val drawable = viewHolder.itemView.background as GradientDrawable
        drawable.color = ContextCompat.getColorStateList(viewHolder.itemView.context, R.color.green)
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val drawable = viewHolder.itemView.background as GradientDrawable
            if (abs(dX) > 200) {
                drawable.color = ContextCompat.getColorStateList(viewHolder.itemView.context, R.color.red)
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    companion object {
        const val TAG = "DragCallBack"
    }

}