package com.zrq.customview.draw

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.util.Log

class PixelLine(
    x: Float,
    y: Float,
    private val size: Float,
    private val color: Int
) : Action(color) {

    override fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.isDither = true
        paint.color = color
        paint.strokeWidth = size
        paint.style = Paint.Style.STROKE
        val floats = FloatArray(points.size * 2) { 0f }
        points.forEachIndexed { index, myPoint ->
            floats[index * 2] = myPoint.x
            floats[index * 2 + 1] = myPoint.y
        }
        Log.d(TAG, "draw: ${floats.toList()}")
        canvas.drawPoints(floats, paint)
    }

    private val points = mutableListOf<MyPoint>()

    override fun move(x: Float, y: Float) {
        val sX = (x / size).toInt() * size
        val sY = (y / size).toInt() * size
        Log.d(TAG, "move: $sX")
        Log.d(TAG, "move: $sY")
        if (!points.contains(MyPoint(sX, sY))) {
            points.add(MyPoint(sX, sY))
        }
    }

    companion object {
        const val TAG = "PixelLine"
    }
}

data class MyPoint(var x: Float, var y: Float) {

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is MyPoint -> other.x == x && other.y == y
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }
}