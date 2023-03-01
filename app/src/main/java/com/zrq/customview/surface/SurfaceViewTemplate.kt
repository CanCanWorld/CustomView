package com.zrq.customview.surface

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class SurfaceViewTemplate : SurfaceView {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private  var mCanvas: Canvas? = null
    private var isDrawing = false

    private fun initView() {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                Thread {
                    while (isDrawing) {
                        drawSomething()
                    }
                }.start()
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
        try {
            mCanvas = holder.lockCanvas()
            mCanvas?.drawColor(Color.WHITE)

        }catch (e: Exception){

        }finally {
            holder.unlockCanvasAndPost(mCanvas)
        }
    }


}