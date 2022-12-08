package com.jacksonbcs.bloodwebpathfinder.main.utils

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.jacksonbcs.bloodwebpathfinder.R
import com.jacksonbcs.bloodwebpathfinder.model.utils.EdgePath
import com.jacksonbcs.bloodwebpathfinder.model.Node
import com.jacksonbcs.bloodwebpathfinder.model.utils.Vertex
import java.lang.Math.floorMod

@BindingAdapter(value = ["app:vertices", "app:ring", "app:position"])
fun setVertex(
    view: ImageView,
    vertices: HashMap<Pair<Int, Int>, Vertex>?,
    ring: Int,
    position: Int
) {

    val vertex = vertices?.get(Pair(ring, position))

    // Set vertex position
    view.translationX = 0F
    view.translationY = 0F
    view.x += (vertex?.xPos ?: 0.0.toFloat())
    view.y += (vertex?.yPos ?: 0.0.toFloat())

    // Set display properties
    val nodeIcon = getNodeIcon(vertex?.node)
    if (vertex != null && nodeIcon != null) {
        view.setImageDrawable(ContextCompat.getDrawable(view.context, nodeIcon))
        view.visibility = View.VISIBLE
    }
    else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:vertices", "app:edges"])
fun drawEdges(
    view: EdgesView,
    vertices: HashMap<Pair<Int, Int>, Vertex>?,
    edges: List<Pair<Vertex, Int>>?
) {
    // Store edge coordinates to be passed to EdgesView, which draws them.
    val edgePaths = mutableListOf<EdgePath>()

    // Offsets to apply to edge path screen coordinates
    val xOffset = view.x + (view.width / 2)
    val yOffset = (-view.y) + (view.height / 2)

    // Iterate over all edges
    edges?.forEach { edge ->

        val srcVertex = edge.first
        getDestinationVertex(vertices, srcVertex, edge.second)?.let { destVertex ->
            val edgePath = EdgePath(
                srcVertex.xPos + xOffset,
                srcVertex.yPos + yOffset,
                destVertex.xPos + xOffset,
                destVertex.yPos + yOffset,
                EdgePath.EdgeType.ACTIVE    // TODO: Can probably get this from destVertex
            )
            edgePaths.add(edgePath)
        }
    }

    // Add the edges from the center (red) node to each of the nodes in the inner ring
    // Must be done separately from the above process because these edges exist regardless
    // of what edges are in `edges`
    for (position in 0..5) {
        // Get vertex from map of vertices in viewmodel
        val vertex = vertices?.get(Pair(0, position))
        vertex?.let {
            // Vertex exists, so build an edge path and add it to the list
            val edgePath = EdgePath(
                it.xPos + xOffset,
                it.yPos + yOffset,
                xOffset,
                yOffset,
                EdgePath.EdgeType.ACTIVE    // TODO: Generalize this
            )
            edgePaths.add(edgePath)
        }
    }

    // Pass edges and redraw the canvas
    view.edges = edgePaths
    view.invalidate()
}

private fun getDestinationVertex(
    vertices: HashMap<Pair<Int, Int>, Vertex>?,
    srcVertex: Vertex,
    neighborSequence: Int
): Vertex? {

    // Coordinates here refers to graph coordinates on the web (ring, position)
    val destVertexCoordinates = when (srcVertex.node.ring) {
        0 -> getInnerRingNodeNeighborCoords(srcVertex.node.position, neighborSequence)
        1 -> getMidRingNodeNeighborCoords(srcVertex.node.position, neighborSequence)
        2 -> getOuterRingNodeNeighborCoords(srcVertex.node.position, neighborSequence)
        else -> Pair(srcVertex.node.ring, srcVertex.node.position)  // TODO: Best way to handle?
    }

    // Get destination vertex from the previously acquired coordinates
    return vertices?.get(destVertexCoordinates)
}

// Get graph coordinates of neighbor of inner ring (ring=0) node
private fun getInnerRingNodeNeighborCoords(position: Int, neighborSequence: Int): Pair<Int, Int> {

    return if (neighborSequence == 4) {
        // Destination node is the clockwise neighbor in the same ring
        Pair(0, floorMod(position + 1, 6))
    }
    else {
        // Destination node is in the middle ring
        val q = position * 2
        Pair(1, floorMod(q + neighborSequence - 2, 12))
    }
}

// Get graph coordinates of neighbor of middle ring (ring=1) node
private fun getMidRingNodeNeighborCoords(position: Int, neighborSequence: Int): Pair<Int, Int> {

    return if (neighborSequence == 2) {
        // Destination node is the clockwise neighbor in the same ring
        Pair(1, floorMod(position + 1, 12))
    }
    else {
        Pair(2, floorMod(position + neighborSequence, 12))
    }
}

// Get graph coordinates of neighbor of outer ring (ring=2) node
private fun getOuterRingNodeNeighborCoords(position: Int, neighborSequence: Int): Pair<Int, Int> {

    return if (neighborSequence == 0) {
        // Destination node is the clockwise neighbor in the same ring
        Pair(2, floorMod(position + 1, 12))
    }
    else {
        // This is just its own coords...
        // TODO: Enforce some rules
        Pair(2, position)
    }
}

// Returns null if the node, its color, or its type are null
fun getNodeIcon(node: Node?): Int? {
    // First determine item type
    return when (node?.type) {
        Node.Type.ADDON -> getAddonIcon(node.color)
        Node.Type.ITEM -> getItemIcon(node.color)
        Node.Type.PERK -> getPerkIcon(node.color)
        Node.Type.OFFERING -> getOfferingIcon(node.color)
        else -> null
    }
}

fun getAddonIcon(color: Node.Color?): Int? {
    // Addons can be any color except iridescent
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_addon_active
        Node.Color.YELLOW -> R.drawable.yellow_addon_active
        Node.Color.GREEN -> R.drawable.green_addon_active
        Node.Color.PURPLE -> R.drawable.purple_addon_active
        else -> null
    }
}

fun getItemIcon(color: Node.Color?): Int? {
    // Items can be any color
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_item_active
        Node.Color.YELLOW -> R.drawable.yellow_item_active
        Node.Color.GREEN -> R.drawable.green_item_active
        Node.Color.PURPLE -> R.drawable.purple_item_active
        Node.Color.IRIDESCENT -> R.drawable.iridescent_item_active
        else -> null
    }
}

fun getPerkIcon(color: Node.Color?): Int? {
    // Perks can only be YELLOW, GREEN, or PURPLE
    return when (color) {
        Node.Color.YELLOW -> R.drawable.yellow_perk_active
        Node.Color.GREEN -> R.drawable.green_perk_active
        Node.Color.PURPLE -> R.drawable.purple_perk_active
        else -> null
    }
}

fun getOfferingIcon(color: Node.Color?): Int? {
    // Offerings can be any color
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_offering_active
        Node.Color.YELLOW -> R.drawable.yellow_offering_active
        Node.Color.GREEN -> R.drawable.green_offering_active
        Node.Color.PURPLE -> R.drawable.purple_offering_active
        Node.Color.IRIDESCENT -> R.drawable.iridescent_offering_active
        else -> null
    }
}