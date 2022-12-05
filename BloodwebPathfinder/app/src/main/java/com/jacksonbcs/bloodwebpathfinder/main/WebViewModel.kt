package com.jacksonbcs.bloodwebpathfinder.main

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.*
import com.jacksonbcs.bloodwebpathfinder.databinding.ActivityMainBinding
import com.jacksonbcs.bloodwebpathfinder.model.Node
import com.jacksonbcs.bloodwebpathfinder.model.Vertex
import com.jacksonbcs.bloodwebpathfinder.model.repository.WebRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WebViewModel(private val repository: WebRepository) : ViewModel() {

    private val nodes = repository.allNodes.asLiveData()

    val web: LiveData<HashMap<Pair<Int, Int>, Vertex>> = Transformations.map(nodes) { nodeList ->
        val nodeMap = HashMap<Pair<Int, Int>, Vertex>()

        for (node in nodeList) {
            val vertex = Vertex(node, webRadius ?: DEFAULT_RADIUS)
            nodeMap[Pair(node.ring, node.position)] = vertex
        }
        nodeMap
    }

    var webRadius: Int? = null
        set(value) {
            if (value != null) {
                field = if (value > 0) value
                else null
            }
        }

    var vertex_0_0: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(0, 0)) ?: NULL_VERTEX
    }

    var vertex_0_1: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(0, 1)) ?: NULL_VERTEX
    }

    var vertex_0_2: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(0, 2)) ?: NULL_VERTEX
    }

    var vertex_0_3: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(0, 3)) ?: NULL_VERTEX
    }

    var vertex_0_4: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(0, 4)) ?: NULL_VERTEX
    }

    var vertex_0_5: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(0, 5)) ?: NULL_VERTEX
    }

    var vertex_1_0: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 0)) ?: NULL_VERTEX
    }

    var vertex_1_1: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 1)) ?: NULL_VERTEX
    }

    var vertex_1_2: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 2)) ?: NULL_VERTEX
    }

    var vertex_1_3: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 3)) ?: NULL_VERTEX
    }

    var vertex_1_4: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 4)) ?: NULL_VERTEX
    }

    var vertex_1_5: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 5)) ?: NULL_VERTEX
    }

    var vertex_1_6: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 6)) ?: NULL_VERTEX
    }

    var vertex_1_7: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 7)) ?: NULL_VERTEX
    }

    var vertex_1_8: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 8)) ?: NULL_VERTEX
    }

    var vertex_1_9: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 9)) ?: NULL_VERTEX
    }

    var vertex_1_10: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 10)) ?: NULL_VERTEX
    }

    var vertex_1_11: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(1, 11)) ?: NULL_VERTEX
    }

    var vertex_2_0: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 0)) ?: NULL_VERTEX
    }

    var vertex_2_1: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 1)) ?: NULL_VERTEX
    }

    var vertex_2_2: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 2)) ?: NULL_VERTEX
    }

    var vertex_2_3: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 3)) ?: NULL_VERTEX
    }

    var vertex_2_4: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 4)) ?: NULL_VERTEX
    }

    var vertex_2_5: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 5)) ?: NULL_VERTEX
    }

    var vertex_2_6: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 6)) ?: NULL_VERTEX
    }

    var vertex_2_7: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 7)) ?: NULL_VERTEX
    }

    var vertex_2_8: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 8)) ?: NULL_VERTEX
    }

    var vertex_2_9: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 9)) ?: NULL_VERTEX
    }

    var vertex_2_10: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 10)) ?: NULL_VERTEX
    }

    var vertex_2_11: LiveData<Vertex> = Transformations.map(web) {
        web.value?.get(Pair(2, 11)) ?: NULL_VERTEX
    }

    fun onNodeClick(ring: Int, position: Int) {

        val node = web.value?.get(Pair(ring, position))?.node
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

        private val NULL_VERTEX = Vertex(
            Node(0, 0, null, null, mutableListOf()),
            0
        )

        private const val DEFAULT_RADIUS = 400
        private const val TAG = "WebViewModel"
    }
}

