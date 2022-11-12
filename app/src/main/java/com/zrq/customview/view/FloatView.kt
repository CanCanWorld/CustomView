package com.zrq.customview.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Matrix
import android.graphics.Point
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import com.google.android.material.imageview.ShapeableImageView
import com.zrq.customview.MainActivity
import com.zrq.customview.R
import kotlin.math.sqrt


class FloatView : FrameLayout, View.OnTouchListener {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    lateinit var imageView: ShapeableImageView

    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        val lp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        lp.topMargin = 399
        layoutParams = lp
        imageView = ShapeableImageView(context)
        imageView.setImageResource(R.mipmap.ic_launcher)
        addView(imageView)
        imageView.setOnTouchListener(this)
    }


    var downX = 0f;
    var downY = 0f;
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val oldFirstPoint = Point()
        val oldSecondPoint = Point()
        if (event != null) {
            var x = event.x.toInt()
            var y = event.y.toInt()
            val matrix = Matrix()
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d(MainActivity.TAG, "ACTION_DOWN")
                    imageView.scaleType = ImageView.ScaleType.MATRIX
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    Log.d(MainActivity.TAG, "ACTION_POINTER_DOWN")
                    oldFirstPoint.set(x, y)
                    x = event.getX(1).toInt()
                    y = event.getY(1).toInt()
                    oldSecondPoint.set(x, y)
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.d(MainActivity.TAG, "ACTION_MOVE")
                    if (event.pointerCount >= 2) {
                        val x1 = event.x.toInt()
                        val y1 = event.y.toInt()
                        val dis = getDistance(x, y, x1, y1)
                        val distance = getDistance(oldFirstPoint.x, oldFirstPoint.y, oldSecondPoint.x, oldSecondPoint.y)
                        val scale = dis * 1f / distance
                        matrix.set(imageView.imageMatrix)
                        matrix.postScale(scale, scale, (x + x1) / 2f, (y + y1) / 2f)
                        imageView.imageMatrix = matrix
                        oldFirstPoint.set(x, y)
                        oldSecondPoint.set(x1, y1)
                    }
                }
                MotionEvent.ACTION_CANCEL -> {
                }
                MotionEvent.ACTION_UP -> {
                    Log.d(MainActivity.TAG, "ACTION_UP")
                    val rectF = RectF(0f, 0f, imageView.drawable.intrinsicWidth.toFloat(), imageView.drawable.intrinsicHeight.toFloat())
                    imageView.imageMatrix.mapRect(rectF)
                    val postX = imageView.width / 2 - (rectF.right + rectF.left) / 2
                    val postY = imageView.height / 2 - (rectF.bottom + rectF.top) / 2
                    matrix.set(imageView.imageMatrix)
                    matrix.postTranslate(postX, postY)
                    imageView.imageMatrix = matrix
                }
                else -> {}
            }
        }
        return true
    }


    private fun getDistance(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        return sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)).toDouble()).toInt()
    }

    fun getScreenHeight(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.width
    }

    fun getScreenWidth(context: Context): Int {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm.defaultDisplay.height
    }
}