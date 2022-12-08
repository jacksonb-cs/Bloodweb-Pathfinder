package com.jacksonbcs.bloodwebpathfinder.main.utils

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
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
    // TODO: We do a lot of looping, don't we...?
    //  - Viewmodel
    //  - BindingAdapter (here)
    //  - EdgeView
    //  This is all virtually the same data. Maybe DataBinding wasn't a great choice for
    //  this particular project.
    edges?.forEach { edge ->

        val srcVertex = edge.first
        getDestinationVertex(vertices, srcVertex, edge.second)?.let { destVertex ->
            val edgePath = EdgePath(
                srcVertex.xPos + xOffset,
                srcVertex.yPos + yOffset,
                destVertex.xPos + xOffset,
                destVertex.yPos + yOffset,
                getEdgeType(srcVertex.node.state, destVertex.node.state)
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
                getCenterEdgeType(it.node.state)
            )
            edgePaths.add(edgePath)
        }
    }

    // Pass edges and redraw the canvas
    view.edges = edgePaths
    view.invalidate()
}

private fun getEdgeType(srcState: Node.State?, destState: Node.State?): Node.State {

    /*
     * TODO:
     *  There are some cases where bought nodes activate edges that this doesn't catch.
     *  I'm just going to deal with that in the simulation for this project.
     */
    return if (srcState == Node.State.BOUGHT && destState == Node.State.BOUGHT) {
        // TODO: This isn't technically true, but we would need to store the order of node
        //  purchases in order to actually emulate the game's behavior...
        Node.State.BOUGHT
    }
    else if (
        (srcState == Node.State.BOUGHT && destState != Node.State.CONSUMED)
        || (destState == Node.State.BOUGHT && srcState != Node.State.CONSUMED)
    ) {
        Node.State.ACTIVE
    }
    else if (srcState == Node.State.CONSUMED && destState == Node.State.CONSUMED) {
        Node.State.CONSUMED
    }
    else if (
        srcState == Node.State.INACTIVE
        || destState == Node.State.INACTIVE
        || srcState == Node.State.CONSUMED
        || destState == Node.State.CONSUMED
    ) {
        Node.State.INACTIVE
    }
    else {
        Node.State.ACTIVE
    }
}

private fun getCenterEdgeType(nodeState: Node.State?): Node.State {

    val genericEdgeType = getEdgeType(nodeState, nodeState)
    // Behavior functions similarly to generic edges, only these cannot be consumed edges
    return if (genericEdgeType == Node.State.CONSUMED)
        Node.State.INACTIVE
    else
        genericEdgeType
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

fun getNodeIcon(node: Node?): Int? {
    return when (node?.state) {
        Node.State.INACTIVE -> getInactiveNodeIcon(node)
        Node.State.ACTIVE -> getActiveNodeIcon(node)
        Node.State.BOUGHT -> getBoughtNodeIcon(node)
        Node.State.CONSUMED -> getConsumedNodeIcon(node)
        else -> null
    }
}