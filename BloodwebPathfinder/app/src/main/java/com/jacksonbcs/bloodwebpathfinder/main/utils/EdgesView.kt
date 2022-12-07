package com.jacksonbcs.bloodwebpathfinder.main.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.jacksonbcs.bloodwebpathfinder.R
import com.jacksonbcs.bloodwebpathfinder.model.EdgePath

class EdgesView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val activePathPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val boughtPathPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    var edges: List<EdgePath> = mutableListOf()
    var path = Path()

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

        // Draw every edge here
        edges.forEach { edge ->
            path.reset()
            path.moveTo(edge.srcX, edge.srcY)
            path.lineTo(edge.destX, edge.destY)
            canvas.drawPath(path, activePathPaint)  // TODO: Change paint according to edge status
        }
    }

    private companion object {
        const val TAG = "EdgesView"
    }
}