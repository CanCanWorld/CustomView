package com.zrq.customview.drag

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zrq.customview.OnItemClickListener
import com.zrq.customview.R
import com.zrq.customview.VH
import com.zrq.customview.databinding.ItemDragViewBinding

class DragAdapter(
    private val context: Context,
    private val list: MutableList<String>,
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<VH<ItemDragViewBinding>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH<ItemDragViewBinding> {
        val mBinding = ItemDragViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return VH(mBinding)
    }

    override fun onBindViewHolder(holder: VH<ItemDragViewBinding>, position: Int) {
        holder.binding.apply {
            tvTitle.text = list[position]
            root.setOnClickListener {
                onItemClickListener.onItemClick(it, holder.adapterPosition)
            }
            root.animation = AnimationUtils.loadAnimation(context, R.anim.anim_one)
        }
        val drawable = holder.itemView.background as GradientDrawable
        drawable.color = ContextCompat.getColorStateList(holder.itemView.context, R.color.green)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}