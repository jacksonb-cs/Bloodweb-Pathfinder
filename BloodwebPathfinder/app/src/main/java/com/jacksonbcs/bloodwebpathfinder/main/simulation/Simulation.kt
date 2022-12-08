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
class Simulation(val viewModel: WebViewModel, speed: SimulationSpeed) {

    // TODO: Determine whether this separation of logic is useful at all
//    val player = Player()
//    val adversary = Adversary()
    private val simSpeed = speed.value
    private val vertices = viewModel.vertices.value

    // Note: All state-changing functions have a delay equal to `simSpeed`
    suspend fun start() {

        var adversaryVertex = vertices?.get(Pair(2, 11))

        buyNode(getVertex(0, 1))
        buyNode(getVertex(1, 1))
    }

    private fun getVertex(ring: Int, position: Int): Vertex? {
        return vertices?.get(Pair(ring, position))
    }

    // Set a node's state to "bought"
    private suspend fun buyNode(vertex: Vertex?) {
        vertex?.let {
            vertex.node.state = Node.State.BOUGHT
            viewModel.update(vertex.node)
            delay(simSpeed)
        }
    }

    // Set a node's state to "consumed"
    private suspend fun consumeNode(vertex: Vertex?) {
        vertex?.let {
            vertex.node.state = Node.State.CONSUMED
            viewModel.update(vertex.node)
            delay(simSpeed)
        }
    }

    // Set a node's state to "active"
    private suspend fun activateNode(vertex: Vertex?) {
        vertex?.let {
            vertex.node.state = Node.State.ACTIVE
            viewModel.update(vertex.node)
            delay(simSpeed)
        }
    }

    // Reset the state of all nodes in the graph to active
    private suspend fun resetNodeStates(vertex: Vertex) {
        // TODO: This should probably go in the viewmodel + the appbar menu get an option for it
    }

    enum class SimulationSpeed(val value: Long) {
        SLOW(750),
        MEDIUM(500),
        FAST(250)
    }
}