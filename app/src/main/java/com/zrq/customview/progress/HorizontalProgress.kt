package com.zrq.customview.progress

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import com.zrq.customview.Util.dp2px
import java.lang.Integer.min

class HorizontalProgress : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var max = 100
    var progress = 0

    private var defaultWidth = dp2px(context, 200)
    private var defaultHeight = dp2px(context, 20)
    private var unReachedBarColor = Color.WHITE
    private var reachedBarColor = Color.GREEN
    private var mWidth = 0
    private var mHeight = 0
    private val paint = Paint()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        mWidth = if (widthMode == MeasureSpec.EXACTLY) {
            widthMeasureSpec
        } else {
            min(defaultWidth, widthMeasureSpec)
        }
        mHeight = if (heightMode == MeasureSpec.EXACTLY) {
            heightMeasureSpec
        } else {
            min(defaultHeight, heightMeasureSpec)
        }
        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()

        paint.color = unReachedBarColor
        paint.strokeWidth = mHeight.toFloat()
        canvas?.drawLine(0f, 0f, mWidth.toFloat(), 0f, paint)

        canvas?.translate(paddingLeft.toFloat(), height / 2.0f)
        val rate = progress * 1.0f / max

        val progressPos = (mWidth * rate).toInt()

        paint.measureText("$progress%")


        canvas?.restore()
    }


}