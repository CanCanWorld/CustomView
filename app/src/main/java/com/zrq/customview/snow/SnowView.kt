package com.zrq.customview.snow

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.ColorUtils
import com.zrq.customview.Util.dp2px
import com.zrq.customview.Util.randomWhite
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min
import kotlin.random.Random

class SnowView : View {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint: Paint = Paint()

    private val bobbleList = ArrayList<Bobble>()

    private val defaultWidth = dp2px(context, 100)
    private val defaultHeight = dp2px(context, 100)

    private var measureWidth = 0
    private var measureHeight = 0

    val random = Random(Date().time)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        measureWidth = if (widthMode == MeasureSpec.EXACTLY) {
            widthSize
        } else {
            min(widthSize, defaultWidth)
        }

        measureHeight = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            min(heightSize, defaultHeight)
        }

        measureWidth = measureWidth - paddingStart - paddingEnd
        measureHeight = measureHeight - paddingTop - paddingBottom

        setMeasuredDimension(measureWidth, measureHeight)

    }

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        for (i in 0..measureWidth / 3) {
            Log.d(TAG, "onLayout: $i")
            val x = random.nextInt(measureWidth)
            val y = random.nextInt(measureHeight)

            val color = randomWhite()
            val bobble = Bobble(Point(x, y), Point(x, 0), color, random.nextInt(3) + 1, random.nextFloat() * 3 + 1)
            bobbleList.add(bobble)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        bobbleList.forEach {
            paint.color = it.color
            canvas?.drawCircle(it.position.x.toFloat(), it.position.y.toFloat(), it.radius, paint)
            it.position.y += it.speed
            it.position.x += random.nextInt(-1, 2)
            if (it.position.y >= measureHeight) it.position.y = 0
        }
        postInvalidateDelayed(20L)
    }


    companion object {
        const val TAG = "SnowView"
    }
}