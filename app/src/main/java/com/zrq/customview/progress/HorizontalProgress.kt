package com.zrq.customview.progress

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.widget.ProgressBar
import com.zrq.customview.Util.dp2px
import java.lang.Integer.min

class HorizontalProgress : ProgressBar {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var defaultWidth = dp2px(context, 400)
    private var defaultHeight = dp2px(context, 50)
    private var unReachedBarColor = Color.YELLOW
    private var reachedBarColor = Color.GREEN
    private var textColor = Color.BLACK
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
            widthSize
        } else {
            min(defaultWidth, widthSize)
        }
        mHeight = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            min(defaultHeight, heightSize)
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

        Log.d(TAG, "progress: $progress")

        var progressPos = mWidth * rate
        val text = "${rate.toInt()}%"

        val textWidth = paint.measureText(text)
        val textHeight = (paint.descent() + paint.ascent()) / 2

        if (progressPos + textWidth > mWidth) {
            progressPos = mWidth - textWidth
        }

        paint.color = reachedBarColor
        paint.strokeWidth = mHeight.toFloat()
        Log.d(TAG, "progressPos: $progressPos")
        canvas?.drawLine(0f, 0f, progressPos, 0f, paint)

        paint.color = textColor
        paint.strokeWidth = 20f
        canvas?.drawText(text, progressPos, -textHeight, paint)

        canvas?.restore()

        postInvalidateDelayed(100)

    }

    companion object{
        const val TAG = "HorizontalProgress"
    }

}