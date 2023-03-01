package com.zrq.customview.draw

import android.graphics.Canvas
import android.graphics.Color

abstract class Action(
    var color: Int = Color.BLACK
) {
    abstract fun draw(canvas: Canvas)

    abstract fun move(x: Float, y: Float)
}