package com.zrq.customview.study

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class CustomView : View {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint()
    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let { canvas ->
            paint.style = Paint.Style.FILL
            canvas.drawCircle(300f, 300f, 200f, paint)
            canvas.drawRect(100f, 100f, 300f, 300f, paint)

            paint.strokeWidth = 45f
            paint.strokeCap = Paint.Cap.SQUARE
            val points = floatArrayOf(50f, 50f, 50f, 100f, 100f, 50f, 100f, 100f)
            canvas.drawPoints(points, paint)
            canvas.drawOval(200f, 50f, 300f, 100f, paint)
            canvas.drawLine(100f, 400f, 500f, 600f, paint)
            canvas.drawRoundRect(300f, 400f, 700f, 600f, 50f, 50f, paint)
            canvas.drawArc(100f, 700f, 400f, 900f, -100f, 100f, true, paint)
            canvas.drawArc(100f, 700f, 400f, 900f, 50f, 140f, false, paint)
            paint.strokeWidth = 15f
            path.addCircle(300f, 1000f, 100f, Path.Direction.CW)
            paint.style = Paint.Style.STROKE
            path.moveTo(400f, 1000f)
            path.lineTo(500f, 1000f)
            canvas.drawPath(path, paint)
        }
    }
}