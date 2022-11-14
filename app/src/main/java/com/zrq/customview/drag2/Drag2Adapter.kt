package com.zrq.customview.drag2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zrq.customview.OnItemClickListener
import com.zrq.customview.databinding.ItemDragViewBinding
import java.util.*

class Drag2Adapter(
    private val context: Context,
    private val list: MutableList<String>,
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<Drag2Adapter.InnerHolder>(), ITouchHelper {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        return InnerHolder(ItemDragViewBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.mBinding.tvTitle.text = list[position]
        holder.mBinding.root.setOnClickListener {
            onItemClickListener.onItemClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class InnerHolder(val mBinding: ItemDragViewBinding) : RecyclerView.ViewHolder(mBinding.root) {

    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
//        Collections.swap(list, fromPosition, toPosition)
        val s = list[fromPosition]
        list.removeAt(fromPosition)
        list.add(toPosition, s)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}