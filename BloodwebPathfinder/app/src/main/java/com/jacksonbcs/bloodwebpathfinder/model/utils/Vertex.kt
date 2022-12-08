package com.jacksonbcs.bloodwebpathfinder.model.utils

import com.jacksonbcs.bloodwebpathfinder.model.Node
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.properties.Delegates

class Vertex(val node: Node, webRadius: Int) {

    // Position on screen
    var xPos by Delegates.notNull<Float>()
    var yPos by Delegates.notNull<Float>()

    init {
        setScreenPosition(webRadius)
    }

    private fun setScreenPosition(webRadius: Int) {

        // Length of arc between nodes
        val arcRadians = circleRadians / ringNumNodes[node.ring]

        // Begin at vertical line (PI / 2) since that is the position of node.position == 0
        val positionAngle = arcRadians * getOffsetSequencePosition() - (PI / 2)

        // X and Y ratios
        val xRatio: Double = cos(positionAngle)
        val yRatio: Double = sin(positionAngle)

        // Adjust the radius according to the ring of the node
        val adjustedRadius = (node.ring + 1) * webRadius.toDouble() / 3

        xPos = (xRatio * adjustedRadius).toFloat()
        yPos = (yRatio * adjustedRadius).toFloat()
    }

    // Return position by which to multiply the arc length between nodes in a ring
    private fun getOffsetSequencePosition(): Double {
        return if (node.ring != 1)
            node.position.toDouble()
        else
        // The middle ring's rotation is offset compared to the other two rings
            node.position.toDouble() + 0.5
    }

    private companion object {
        const val circleRadians = 2 * PI
        val ringNumNodes = arrayListOf(6, 12, 12)
        const val TAG = "VertexClass"
    }
}