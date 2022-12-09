package com.zrq.customview.snow

import android.graphics.Point

data class Bobble(
    var position: Point,
    var origin: Point,
    var color: Int,
    var speed: Int,
    var radius: Float
)