package com.zrq.customview.surface

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class SurfaceViewDraw : SurfaceView {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private var mCanvas: Canvas? = null
    private var isDrawing = false
    private val mPaint = Paint()
    private val mPath = Path()
    private var mX = 0f
    private var mY = 0f
    private var mHolder: SurfaceHolder? = null

    init {
        mPaint.color = Color.BLACK
        mPaint.style = Paint.Style.STROKE
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 5f
    }

    private fun initView() {
        mHolder = holder
        mHolder?.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                isDrawing = true
                drawSomething()
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                isDrawing = false
            }
        })

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            focusable = FOCUSABLE
        }
        keepScreenOn = true
        isFocusableInTouchMode = true
    }

    private fun drawSomething() {
        Thread {
            while (isDrawing) {
                try {
                    mCanvas = mHolder?.lockCanvas()
                    mCanvas?.drawColor(Color.WHITE)
                    mCanvas?.drawPath(mPath, mPaint)
                } catch (e: Exception) {

                } finally {
                    mCanvas?.let {
                        mHolder?.unlockCanvasAndPost(it)
                    }
                }
            }
        }.start()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    mPath.moveTo(it.x, it.y)
                }
                MotionEvent.ACTION_MOVE -> {
                    mPath.lineTo(it.x, it.y)
                }
                else -> {}
            }
        }
        return true
    }


}