package com.zrq.customview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zrq.customview.databinding.ItemDragViewBinding

class DragAdapter(
    private val context: Context,
    private var list: MutableList<String>,
) : RecyclerView.Adapter<DragAdapter.InnerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        return InnerHolder(ItemDragViewBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.mBinding.tvTitle.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class InnerHolder(val mBinding: ItemDragViewBinding) : RecyclerView.ViewHolder(mBinding.root) {

    }
}