package com.zrq.customview.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

class FreeLine(
    x: Float,
    y: Float,
    private val size: Float,
    private val color: Int
) {

    private val path: Path = Path()

    init {
        path.moveTo(x, y)
        path.lineTo(x, y)
    }

    fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.isDither = true
        paint.color = color
        paint.strokeWidth = size
        paint.style = Paint.Style.STROKE
        canvas.drawPath(path, paint)
    }

    fun move(x: Float, y: Float) {
        path.lineTo(x, y)
    }
}