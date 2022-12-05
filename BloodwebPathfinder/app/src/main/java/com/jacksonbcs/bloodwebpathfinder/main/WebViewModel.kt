package com.jacksonbcs.bloodwebpathfinder.main

import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
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
            nodeMap[Pair(node.ring, node.position)] =
                Vertex(node, webRadius ?: DEFAULT_RADIUS)
        }

        // TODO: DELETE
        Log.d(TAG, "NodeMap size: ${nodeMap.size}")
        for ((key, value) in nodeMap) {
            Log.d(TAG, "PRESENT: $key")
        }

//        notifyPropertyChanged(1)  TODO
        nodeMap
    }

    // TODO: DELETE
    init {
//        testLatency()
    }

    // TODO: REMOVE
    var binding: ActivityMainBinding? = null
    fun testLatency() = viewModelScope.launch {
        Log.d(TAG, "TEST LATENCY: BEFORE DELAY")
        delay(5000)
        Log.d(TAG, "AFTER DELAY: TEST LATENCY")
        insert(Node(2, 10, Node.Type.OFFERING, Node.Color.IRIDESCENT, mutableListOf()))
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
            300 // TODO: Set back to zero
        )

        private const val DEFAULT_RADIUS = 400
        private const val TAG = "WebViewModel"
    }
}

