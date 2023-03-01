package com.zrq.customview.draw

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class DoodleView : SurfaceView, SurfaceHolder.Callback {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var curColor = Color.BLACK
    private val paint = Paint()
    private var curAction: Action? = null
    private var curSize = 5
    private var surfaceHolder: SurfaceHolder? = null

    init {
        surfaceHolder = holder
        surfaceHolder?.addCallback(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            focusable = FOCUSABLE

    }

    override fun surfaceCreated(holder: SurfaceHolder) {

    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }
}