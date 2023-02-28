package com.zrq.customview.swipe

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zrq.customview.R
import com.zrq.customview.databinding.FragmentPagerBinding
import kotlin.random.Random

class PagerFragment(private val title: String, private val position: Int) : Fragment() {

    private lateinit var mBinging: FragmentPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinging = FragmentPagerBinding.inflate(inflater, container, false)
        mBinging.apply {
            tvTitle.text = title
            val random = Random(position).nextInt(0, 255)
            root.setBackgroundColor(Color.rgb(random, random, random))
        }
        return mBinging.root
    }

}