package com.zrq.customview.draw

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class DoodleView : SurfaceView, SurfaceHolder.Callback {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    private var curColor = Color.BLACK
    private val paint = Paint()
    private var curAction: Action? = null
    private var curSize = 25f
    private var surfaceHolder: SurfaceHolder? = null
    private val baseActions = mutableListOf<Action>()
    private var curActionType = ActionType.PixelLine
    private var canvas: Canvas? = null

    init {
        surfaceHolder = holder
        surfaceHolder?.addCallback(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            focusable = FOCUSABLE

        paint.color = Color.WHITE
        paint.strokeWidth = curSize

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        canvas = surfaceHolder?.lockCanvas()
        canvas?.drawColor(Color.WHITE)
        surfaceHolder?.unlockCanvasAndPost(canvas)
        baseActions.clear()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    setCurAction(it.x, it.y)
                }
                MotionEvent.ACTION_MOVE -> {
                    canvas = surfaceHolder?.lockCanvas()
                    canvas?.let { canvas ->
                        canvas.drawColor(Color.WHITE)
                        baseActions.forEach { action ->
                            action.draw(canvas)
                        }
                        curAction?.move(it.x, it.y)
                        curAction?.draw(canvas)
                        surfaceHolder?.unlockCanvasAndPost(canvas)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    curAction?.let { action ->
                        baseActions.add(action)
                    }
                    curAction == null
                }
                else -> {}
            }
        }
        return true
    }

    private fun setCurAction(x: Float, y: Float) {
        when (curActionType) {
            ActionType.FreeLine -> {
                curAction = FreeLine(x, y, curSize, curColor)
            }
            ActionType.PixelLine -> {
                curAction = PixelLine(x, y, curSize, curColor)
            }
            ActionType.Line -> {
            }
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

    companion object {
        const val TAG = "DoodleView"
    }
}