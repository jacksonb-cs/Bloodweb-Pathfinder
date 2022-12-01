package com.jacksonbcs.bloodwebpathfinder.model

import junit.framework.Assert.assertEquals
import org.junit.Test


internal class VertexTest {

    private val radius = 1080
    private val color = Node.Color.BROWN.color
    private val type = Node.Type.ADDON.type

    @Test
    fun testCorrectPosition() {

        // ===== Declaration === \\
        val v1 = Vertex(
            Node(0, 0, type, color),
            radius
        )
        val v2 = Vertex(
            Node(1, 0, null, color),
            radius
        )
        val v3 = Vertex(
            Node(2, 0, type, null),
            radius
        )
        val v4 = Vertex(
            Node(0, 4, null, null),
            radius
        )
        val v5 = Vertex(
            Node(1, 2, type, color),
            radius
        )
        val v6 = Vertex(
            Node(2, 4 , type, null),
            radius
        )

        // ===== Testing ===== \\
        assertEquals(String.format("%.4f", v1.angle), "1.5707")
    }
}