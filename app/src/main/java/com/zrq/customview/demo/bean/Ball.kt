package com.zrq.customview.demo.bean

import android.graphics.Point

data class Ball(
    var point: Point,
    var color: Int,
    var radius : Int,
    var speed: Int,
    var rateX: Float,
    var rateY: Float,
)