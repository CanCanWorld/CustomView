package com.zrq.customview.demo.bean

import android.content.Context
import android.util.AttributeSet
import android.view.View

class MoveView: View {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mWidth = 0
    private var mHeight = 0
}
