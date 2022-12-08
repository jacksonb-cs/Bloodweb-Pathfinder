package com.jacksonbcs.bloodwebpathfinder.main.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.jacksonbcs.bloodwebpathfinder.R
import com.jacksonbcs.bloodwebpathfinder.model.Node
import com.jacksonbcs.bloodwebpathfinder.model.utils.EdgePath

class EdgesView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val inactivePathPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val activePathPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val boughtPathPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val consumedPathPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val strokeWidth = 10F

    var edges: List<EdgePath> = mutableListOf()
    var path = Path()

    init {
        inactivePathPaint.strokeWidth = strokeWidth
        inactivePathPaint.style = Paint.Style.STROKE
        inactivePathPaint.color = ContextCompat.getColor(context, R.color.inactive_edge)

        activePathPaint.strokeWidth = strokeWidth
        activePathPaint.style = Paint.Style.STROKE
        activePathPaint.color = ContextCompat.getColor(context, R.color.active_edge)

        boughtPathPaint.strokeWidth = strokeWidth
        boughtPathPaint.style = Paint.Style.STROKE
        boughtPathPaint.color = ContextCompat.getColor(context, R.color.blood_red)

        consumedPathPaint.strokeWidth = strokeWidth
        consumedPathPaint.style = Paint.Style.STROKE
        consumedPathPaint.color = ContextCompat.getColor(context, R.color.black)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw every edge here
        edges.forEach { edge ->
            path.reset()
            path.moveTo(edge.srcX, edge.srcY)
            path.lineTo(edge.destX, edge.destY)

            val paint = when (edge.type) {
                Node.State.ACTIVE -> activePathPaint
                Node.State.INACTIVE -> inactivePathPaint
                Node.State.BOUGHT -> boughtPathPaint
                Node.State.CONSUMED -> consumedPathPaint
            }

            canvas.drawPath(path, paint)
        }
    }

    private companion object {
        const val TAG = "EdgesView"
    }
}