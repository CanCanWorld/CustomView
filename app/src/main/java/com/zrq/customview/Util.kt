package com.zrq.customview

import android.content.Context
import android.graphics.Color
import android.util.Log
import java.util.*
import kotlin.random.Random

object Util {
    //一个 dp 转 像素的计算
    fun dp2px(context: Context, dp: Int): Int {
        val density: Float = context.resources.displayMetrics.density
        return (dp * density + 0.5f).toInt()
    }

    fun randomWhite(): Int {
        return Color.argb(Random.nextInt(0xff), 0xff, 0xff, 0xff)
    }

    fun randomColor(): Int {
        val r1 =  Random.nextInt(0xff)
        val r2 =  Random.nextInt(0xff)
        val r3 =  Random.nextInt(0xff)
        val r4 =  Random.nextInt(0xff)
        return Color.argb(r1, r2, r3, r4)
    }

    private const val TAG = "Util"
}