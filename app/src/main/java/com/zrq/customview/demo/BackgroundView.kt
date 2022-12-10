package com.zrq.customview.demo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.zrq.customview.Util.dp2px
import com.zrq.customview.Util.randomColor
import com.zrq.customview.demo.bean.Pling
import kotlin.collections.ArrayList
import kotlin.math.min
import kotlin.random.Random

class BackgroundView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint()
    private val paint2 = Paint()

    private var defaultWidth = dp2px(context, 200)
    private var defaultHeight = dp2px(context, 300)

    private var mWidth = 0
    private var mHeight = 0

    private val random = Random

    private val list = ArrayList<Pling>()

    private var time: Int = 0

    init {
        paint2.color = Color.argb(0x02, 0xff, 0xff, 0xff)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
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

        setMeasuredDimension(mWidth, mHeight)

    }

    @SuppressLint("DrawAllocation")
    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        for (i in 0..1000) {

            val point = Point(random.nextInt(mWidth), -10)
            val radius = random.nextInt(3) * 5 + 10
            val color = randomColor()
            val speed = random.nextInt(3) + 2
            val delay = random.nextInt(20000)

            val pling = Pling(point, radius, color, speed, delay)

            list.add(pling)
        }
    }


    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        list.forEach {
            if (time > it.delay) {
                paint.color = it.color
                it.point.y += it.speed
                it.point.x += random.nextInt(-1, 2)
                if (it.point.y > mHeight) {
                    it.point.y = 0
                }
                if (it.point.x < 0) {
                    it.point.x = mWidth
                }
                if (it.point.x > mWidth) {
                    it.point.x = 0
                }
                canvas?.drawCircle(it.point.x.toFloat(), it.point.y.toFloat(), it.radius.toFloat(), paint)
            }
        }

        time += 20
        postInvalidateDelayed(20)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {
            }
            else -> {}
        }
        return true
    }

    companion object {
        const val TAG = "BackgroundView"
    }
}