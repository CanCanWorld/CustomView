package com.zrq.customview.seekbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import com.google.android.material.internal.TextDrawableHelper
import com.zrq.customview.Util

class CustomSeekbar : AppCompatSeekBar {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint()

    private var defaultWidth = Util.dp2px(context, 400)
    private var defaultHeight = Util.dp2px(context, 50)
    private var mWidth = 0
    private var mHeight = 0


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        mWidth = if (widthMode == MeasureSpec.EXACTLY) {
            widthSize
        } else {
            Integer.min(defaultWidth, widthSize)
        }
        mHeight = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            Integer.min(defaultHeight, heightSize)
        }
        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.BLACK
        paint.textSize = 20f
    }
}