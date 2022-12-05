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

// TODO: May need to remove "Observable" implementation
class WebViewModel(private val repository: WebRepository) : ViewModel(), Observable {

    private val callbacks = PropertyChangeRegistry()

    private val nodes = repository.allNodes.asLiveData()
    var webRadius: Int? = null
        set(value) {
            if (value != null) {
                field = if (value > 0) value
                else null
            }
        }

    val web: LiveData<HashMap<Pair<Int, Int>, Vertex>> = Transformations.map(nodes) { nodeList ->
        val nodeMap = HashMap<Pair<Int, Int>, Vertex>()

        for (node in nodeList) {
            val vertex = Vertex(node, webRadius ?: DEFAULT_RADIUS)
            nodeMap[Pair(node.ring, node.position)] = vertex
        }
        // TODO: DELETE
//        Log.d(TAG, "NodeMap size: ${nodeMap.size}")
//        for ((key, value) in nodeMap) {
//            Log.d(TAG, "PRESENT: $key")
//        }

//        notifyPropertyChanged(1)  TODO
        nodeMap
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

    // TODO: DELETE
    init {
        testLatency()
    }

    // TODO: REMOVE
    var binding: ActivityMainBinding? = null
    fun testLatency() = viewModelScope.launch {
        Log.d(TAG, "TEST LATENCY: BEFORE DELAY")
        delay(5000)
        Log.d(TAG, "AFTER DELAY: TEST LATENCY")
        insert(Node(2, 10, Node.Type.OFFERING, Node.Color.IRIDESCENT, mutableListOf()))
        insert(Node(0, 3, Node.Type.PERK, Node.Color.GREEN, mutableListOf()))
//        binding?.invalidateAll()
//        binding?.executePendingBindings()
    }

    fun getVertex(ring: Int, position: Int): Vertex {
        // TODO: REMOVE
        Log.d(TAG, "${web.value?.size}")
        Log.d(TAG, "Ring: $ring; Position: $position; Web.value: ${web.value?.get(Pair(ring, position))}")

        return web.value?.get(Pair(ring, position)) ?: NULL_VERTEX
    }

    fun insert(node: Node) = viewModelScope.launch {
        repository.insert(node)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    // TODO: Maybe fix, maybe delete
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

    // TODO: DELETE
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
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

