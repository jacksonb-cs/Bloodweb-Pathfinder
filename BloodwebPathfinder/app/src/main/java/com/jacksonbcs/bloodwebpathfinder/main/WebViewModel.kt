package com.jacksonbcs.bloodwebpathfinder.main

import android.util.Log
import androidx.lifecycle.*
import com.jacksonbcs.bloodwebpathfinder.model.Node
import com.jacksonbcs.bloodwebpathfinder.model.Vertex
import com.jacksonbcs.bloodwebpathfinder.model.Web
import com.jacksonbcs.bloodwebpathfinder.model.repository.WebRepository
import kotlinx.coroutines.launch

class WebViewModel(private val repository: WebRepository) : ViewModel() {

    private val rawWebData = repository.allNodes.asLiveData()

    private val web: LiveData<Web> =
        Transformations.map(rawWebData) { nodeList ->
            val nodeMap = HashMap<Pair<Int, Int>, Vertex>()
            val edgeList = mutableListOf<Pair<Vertex, Int>>()

            for (node in nodeList) {
                // Wrap nodes in vertices and later store in web
                val vertex = Vertex(node, webRadius ?: DEFAULT_RADIUS)
                nodeMap[Pair(node.ring, node.position)] = vertex

                // Store this node's child* edges
                //  (*defined by my own arbitrary definitions, forgive me)
                for (neighbor in node.neighbors) {
                    edgeList.add(Pair(vertex, neighbor))
                }
            }

            Web(MutableLiveData(nodeMap), MutableLiveData(edgeList))
    }

    // HashMap: <ring, position> -> Vertex
    val vertices: LiveData<HashMap<Pair<Int, Int>, Vertex>?> =
        Transformations.map(web) { web ->
            web.vertices.value
        }

    // List of all existing edges in web
    val edges: LiveData<List<Pair<Vertex, Int>>> =
        Transformations.map(web) { web ->
            web.edges.value ?: mutableListOf()
        }

    var webRadius: Int? = null
        set(value) {
            if (value != null) {
                field = if (value > 0) value
                else null
            }
        }

    fun onNodeClick(ring: Int, position: Int) {

        val node = vertices.value?.get(Pair(ring, position))?.node
        node?.let {
            node.cycleColor()
            update(node)
        }
    }

    fun insert(node: Node) = viewModelScope.launch {
        repository.insert(node)
    }

    fun update(node: Node) = viewModelScope.launch {
        repository.update(node)
    }

    companion object {

        private const val DEFAULT_RADIUS = 400
        private const val TAG = "WebViewModel"
    }
}

