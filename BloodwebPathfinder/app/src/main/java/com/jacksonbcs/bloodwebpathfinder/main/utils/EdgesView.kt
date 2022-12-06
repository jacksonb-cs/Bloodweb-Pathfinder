package com.jacksonbcs.bloodwebpathfinder.main.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.jacksonbcs.bloodwebpathfinder.R

class EdgesView(context: Context, attrs: AttributeSet) : View(context, attrs) {

//    val edges = mutableListOf<>()
//        HashMap<Pair<Float, Float>, MutableList<Pair<Float, Float>>>(WEB_SIZE)
    private val activePathPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val boughtPathPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        boughtPathPaint.strokeWidth = 12F
        boughtPathPaint.style = Paint.Style.STROKE
        boughtPathPaint.color = ContextCompat.getColor(context, R.color.blood_red)

        activePathPaint.strokeWidth = 12F
        activePathPaint.style = Paint.Style.STROKE
        activePathPaint.color = ContextCompat.getColor(context, R.color.activated_edge)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        edgePath.reset()
//        edgePath.moveTo(10F, 10F)
//        edgePath.lineTo(50F, 100F)
//        canvas.drawPath(edgePath, activePathPaint)
    }

    private companion object {
        // TODO: Possibly delete
        // 6 * 6 + 12 * 3 + 12 * 1
        const val MAX_EDGE_COUNT = 84

        const val WEB_SIZE = 30
    }
}