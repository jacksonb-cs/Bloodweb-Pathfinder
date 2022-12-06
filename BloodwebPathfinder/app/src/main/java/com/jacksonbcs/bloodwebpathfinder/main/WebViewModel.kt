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
    val vertices: LiveData<java.util.HashMap<Pair<Int, Int>, Vertex>?> =
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

    // TODO: Bind each VertexView to `nodes`,
    //  and give them back their <ring, position> attributes!
    //  Can't believe I didn't think of this before...
    var vertex_0_0: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(0, 0)) ?: NULL_VERTEX
    }

    var vertex_0_1: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(0, 1)) ?: NULL_VERTEX
    }

    var vertex_0_2: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(0, 2)) ?: NULL_VERTEX
    }

    var vertex_0_3: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(0, 3)) ?: NULL_VERTEX
    }

    var vertex_0_4: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(0, 4)) ?: NULL_VERTEX
    }

    var vertex_0_5: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(0, 5)) ?: NULL_VERTEX
    }

    var vertex_1_0: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 0)) ?: NULL_VERTEX
    }

    var vertex_1_1: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 1)) ?: NULL_VERTEX
    }

    var vertex_1_2: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 2)) ?: NULL_VERTEX
    }

    var vertex_1_3: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 3)) ?: NULL_VERTEX
    }

    var vertex_1_4: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 4)) ?: NULL_VERTEX
    }

    var vertex_1_5: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 5)) ?: NULL_VERTEX
    }

    var vertex_1_6: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 6)) ?: NULL_VERTEX
    }

    var vertex_1_7: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 7)) ?: NULL_VERTEX
    }

    var vertex_1_8: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 8)) ?: NULL_VERTEX
    }

    var vertex_1_9: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 9)) ?: NULL_VERTEX
    }

    var vertex_1_10: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 10)) ?: NULL_VERTEX
    }

    var vertex_1_11: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(1, 11)) ?: NULL_VERTEX
    }

    var vertex_2_0: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 0)) ?: NULL_VERTEX
    }

    var vertex_2_1: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 1)) ?: NULL_VERTEX
    }

    var vertex_2_2: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 2)) ?: NULL_VERTEX
    }

    var vertex_2_3: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 3)) ?: NULL_VERTEX
    }

    var vertex_2_4: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 4)) ?: NULL_VERTEX
    }

    var vertex_2_5: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 5)) ?: NULL_VERTEX
    }

    var vertex_2_6: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 6)) ?: NULL_VERTEX
    }

    var vertex_2_7: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 7)) ?: NULL_VERTEX
    }

    var vertex_2_8: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 8)) ?: NULL_VERTEX
    }

    var vertex_2_9: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 9)) ?: NULL_VERTEX
    }

    var vertex_2_10: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 10)) ?: NULL_VERTEX
    }

    var vertex_2_11: LiveData<Vertex> = Transformations.map(vertices) {
        vertices.value?.get(Pair(2, 11)) ?: NULL_VERTEX
    }

    fun onNodeClick(ring: Int, position: Int) {

        val node = vertices.value?.get(Pair(ring, position))?.node
        node?.let {
            node.cycleColor()
            update(node)
        }
    }

    // TODO: DELETE THIS
    fun testWebLoad() {
        viewModelScope.launch {
            repository.identifyAndLoadWeb("test_web")
        }
    }

    fun insert(node: Node) = viewModelScope.launch {
        repository.insert(node)
    }

    fun update(node: Node) = viewModelScope.launch {
        repository.update(node)
    }

    companion object {

        private val NULL_VERTEX = Vertex(
            Node(0, 0, null, null, mutableListOf()),
            0
        )

        private const val DEFAULT_RADIUS = 400
        private const val TAG = "WebViewModel"
    }
}

