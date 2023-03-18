package com.zrq.customview.pixel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.zrq.customview.R

class PixelDrawView : View {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PixelDrawView)
        pixelSize = typedArray.getInteger(R.styleable.PixelDrawView_pixelSize, pixelSize)
        typedArray.recycle()
    }

    private val paint = Paint()
    private var pixelSize = 10
    private var myPoints = mutableListOf<MyPoint>()

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        paint.strokeWidth = pixelSize.toFloat()
        val points = FloatArray(myPoints.size * 2) { 0f }
        myPoints.forEachIndexed { index, myPoint ->
            points[index * 2] = myPoint.x
            points[index * 2 + 1] = myPoint.y
        }
        canvas?.drawPoints(points, paint)
        Log.d(TAG, "onTouchEvent: $myPoints")
        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                }
                MotionEvent.ACTION_MOVE -> {
                    if (!myPoints.contains(MyPoint(event.x, event.y))) {
                        myPoints.add(MyPoint(event.x, event.y))
                    }
                    Log.d(TAG, "onTouchEvent: $myPoints")
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {}
                else -> {}
            }
        }
        return true
    }

    companion object {
        const val TAG = "PixelDrawView"
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