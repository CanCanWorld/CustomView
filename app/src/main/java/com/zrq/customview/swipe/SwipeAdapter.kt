package com.zrq.customview.swipe

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

class SwipeAdapter(
    private val fm: FragmentManager,
    private val list: MutableList<String>
) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return PagerFragment(list[position], position)
    }
}