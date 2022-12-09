package com.zrq.customview.img

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.*
import androidx.appcompat.widget.AppCompatImageView

class ScaleImageView : AppCompatImageView,
    ViewTreeObserver.OnGlobalLayoutListener,
    ScaleGestureDetector.OnScaleGestureListener,
    View.OnTouchListener {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        gestureDetector = ScaleGestureDetector(context, this)
    }

    private val gestureDetector: ScaleGestureDetector

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

    private var onlyOne = true
    private val scaleMatrix = Matrix()

    override fun onGlobalLayout() {
        if (onlyOne) {
            if (drawable == null) {
                return
            }

            onlyOne = false
        }
    }

    override fun onScale(detector: ScaleGestureDetector?): Boolean {
        drawable ?: return true
        val scaleFactor = detector?.scaleFactor
        if (scaleFactor != null) {
            getInitScale()
            scaleMatrix.postScale(scaleFactor, scaleFactor, detector.focusX, detector.focusY)
            imageMatrix = scaleMatrix
        }
        return true
    }

    override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
        getInitScale()
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        return true
    }

    fun getInitScale(): Float {
        val floats = FloatArray(9)
        scaleMatrix.getValues(floats)
        //获取当前的缩放值
        return floats[Matrix.MSCALE_X]
    }
}