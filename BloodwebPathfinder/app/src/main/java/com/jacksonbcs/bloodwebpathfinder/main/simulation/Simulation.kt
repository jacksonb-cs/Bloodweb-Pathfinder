package com.jacksonbcs.bloodwebpathfinder.main.simulation

import com.jacksonbcs.bloodwebpathfinder.main.WebViewModel
import com.jacksonbcs.bloodwebpathfinder.model.Node
import com.jacksonbcs.bloodwebpathfinder.model.utils.Vertex
import kotlinx.coroutines.delay

/*
 * TODO:
 *  This would be a really fun concept to play around with, but...
 *  As it stands, I do not have time to make much of it.
 *  It is EXTREMELY hard-coded.
 *  Hopefully I can come back to make it both interactive and informative
 *  (maybe after overhauling the way the edges in the graph are stored).
 */
class Simulation(val viewModel: WebViewModel, speed: Speed) {

    private val simSpeed = speed.value
    private val vertices = viewModel.vertices.value

    // Note: Buying and consumption functions have a delay equal to `simSpeed`
    suspend fun start() {

        var adversaryVertex = vertices?.get(Pair(2, 11))

        // What is: How to make your code as impenetrable and inextensible as possible?
        buyNode(getVertex(0, 1), false)
        activateNode(getVertex(1, 1), true)
        buyNode(getVertex(1, 1), false)
        activateNode(getVertex(2, 1), false)
        activateNode(getVertex(2, 2), true)
        buyNode(getVertex(2, 1), true)
        consumeNode(getVertex(2, 0), true)
        buyNode(getVertex(0, 5), true)
        consumeNode(getVertex(1, 0), true)
        buyNode(getVertex(0, 3), false)
        activateNode(getVertex(1, 6), true)
        consumeNode(getVertex(0, 0), true, simSpeed / 4)
        consumeNode(getVertex(1, 11), true, simSpeed / 4)
        consumeNode(getVertex(2, 11), true)
        buyNode(getVertex(0, 4), false)
        activateNode(getVertex(1, 7), true)
        consumeNode(getVertex(2, 2), true)
        buyNode(getVertex(1, 7), true)
        consumeNode(getVertex(2, 7), true)
        buyNode(getVertex(0, 2), true)
        consumeNode(getVertex(1, 6), false)
    }

    private fun getVertex(ring: Int, position: Int): Vertex? {
        return vertices?.get(Pair(ring, position))
    }

    // Set a node's state to "bought"
    private suspend fun buyNode(vertex: Vertex?, useDelay: Boolean) {
        vertex?.let {
            vertex.node.state = Node.State.BOUGHT
            viewModel.update(vertex.node)
            // Delay is sometimes bad if it is, for example, before a call to `activateNode`
            if (useDelay)
                delay(simSpeed)
        }
    }

    // Set a node's state to "consumed"
    // Can set simSpeed here for a nice little animation when a branch is eaten
    private suspend fun consumeNode(vertex: Vertex?, useDelay: Boolean, delay: Long = simSpeed) {
        vertex?.let {
            vertex.node.state = Node.State.CONSUMED
            viewModel.update(vertex.node)

            if (useDelay)
                delay(delay)
        }
    }

    // Set a node's state to "active"
    private suspend fun activateNode(vertex: Vertex?, useDelay: Boolean) {
        vertex?.let {
            vertex.node.state = Node.State.ACTIVE
            viewModel.update(vertex.node)

            if (useDelay)
                delay(simSpeed)
        }
    }

    // Reset the state of all nodes in the graph to active
    private suspend fun resetNodeStates(vertex: Vertex) {
        // TODO: This should probably go in the viewmodel + the appbar menu get an option for it
    }

    enum class Speed(val value: Long) {
        SLOW(1250),
        MEDIUM(500),
        FAST(200)
    }
}