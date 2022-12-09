package com.zrq.customview.demo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.zrq.customview.demo.bean.Ball
import kotlin.math.min

class Background2View : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val ballPaint = Paint()
    private val linePaint = Paint()
    private var mWidth = 0
    private var mHeight = 0

    private val defaultWidth = 200
    private val defaultHeight = 300

    private val ball: Ball = Ball(Point(-100, -100), Color.BLACK, 20, 0, 0f, 0f)

    init {
        linePaint.color = Color.argb(0x77, 0x4c, 0xaf, 0x50)
        linePaint.strokeWidth = 10f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        mWidth = if (widthMode == MeasureSpec.EXACTLY) {
            widthSize
        } else {
            min(widthSize, defaultWidth)
        }

        mHeight = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            min(heightSize, defaultHeight)
        }

    }

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        ball.point = Point(mWidth / 2 - ball.radius, mHeight / 2 - ball.radius)
        ball.speed = 10
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        postInvalidateDelayed(20)
        ball.point.x += (ball.speed * ball.rateX).toInt()
        ball.point.y += (ball.speed * ball.rateY).toInt()

        if (ball.point.x > mWidth) {
            ball.point.x = 0 - ball.radius * 2
        }

        if (ball.point.x < 0 - ball.radius * 2) {
            ball.point.x = mWidth + ball.radius * 2
        }

        if (ball.point.y > mHeight) {
            ball.point.y = 0 - ball.radius * 2
        }

        if (ball.point.y < 0 - ball.radius * 2) {
            ball.point.y = mHeight + ball.radius * 2
        }
        ballPaint.color = ball.color
        canvas?.drawCircle(ball.point.x.toFloat(), ball.point.y.toFloat(), ball.radius.toFloat(), ballPaint)

        canvas?.drawLines(floatArrayOf(startPoint.x.toFloat(), startPoint.y.toFloat(), endPoint.x.toFloat(), endPoint.y.toFloat()), linePaint)
    }

    private val startPoint = Point()
    private val endPoint = Point()

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startPoint.x = event.x.toInt()
                startPoint.y = event.y.toInt()
                endPoint.x = event.x.toInt()
                endPoint.y = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                endPoint.x = event.x.toInt()
                endPoint.y = event.y.toInt()
                ball.rateX = 1.0f * (endPoint.y - startPoint.x) / (endPoint.y - startPoint.y + endPoint.y - startPoint.x)
                ball.rateY = 1.0f * (endPoint.y - startPoint.y) / (endPoint.y - startPoint.y + endPoint.y - startPoint.x)
                Log.d(TAG, "rateX: ${ball.rateX}")
                Log.d(TAG, "rateY: ${ball.rateY}")
                Log.d(TAG, "count: ${ball.rateY + ball.rateX}")
            }
            MotionEvent.ACTION_UP -> {
                endPoint.x = 0
                endPoint.y = 0
                startPoint.x = 0
                startPoint.y = 0
            }
            else -> {}
        }

        return true
    }


    companion object {
        const val TAG = "Background2View"
    }
}