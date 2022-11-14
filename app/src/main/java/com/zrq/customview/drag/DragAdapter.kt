package com.zrq.customview.drag

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zrq.customview.OnItemClickListener
import com.zrq.customview.VH
import com.zrq.customview.databinding.ItemDragViewBinding

class DragAdapter(
    private val context: Context,
    private val list: MutableList<String>,
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<VH<ItemDragViewBinding>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH<ItemDragViewBinding> {
        val mBinding =  ItemDragViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return VH(mBinding)
    }

    override fun onBindViewHolder(holder: VH<ItemDragViewBinding>, position: Int) {
        holder.binding.tvTitle.text = list[position]
        holder.binding.root.setOnClickListener {
            onItemClickListener.onItemClick(it, holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}