package com.jacksonbcs.bloodwebpathfinder.model.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.asLiveData
import com.jacksonbcs.bloodwebpathfinder.model.Node
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class WebRepository(
    private val nodeRoomDao: NodeRoomDao,
    private val nodeFirestoreDao: NodeFirestoreDao
) {
    val allNodes: Flow<List<Node>> = nodeRoomDao.getAllNodes()

    @WorkerThread
    suspend fun insert(node: Node) {
        nodeRoomDao.insert(node)
    }

    @WorkerThread
    suspend fun update(node: Node) {
        nodeRoomDao.update(node)
    }

    @WorkerThread
    suspend fun identifyAndLoadWeb(filename: String) {

        clearWeb()

        // Get nodes from Firestore
        nodeFirestoreDao.loadWebByFilename(filename) { nodeData ->
            // Firestore DAO uses this callback to insert locally
            insert(buildNode(nodeData))
        }
    }

    @WorkerThread
    suspend fun clearWeb() {
        nodeRoomDao.deleteAll()
    }

    private fun buildNode(map: Map<String, Any>): Node {

        return Node(
            (map["ring"] as Long).toInt(),
            (map["position"] as Long).toInt(),
            Node.Type.fromInt((map["type"] as Long).toInt()),
            Node.Color.fromInt((map["color"] as Long).toInt()),
            decodeNeighbors(map["neighbors"] as String)
        )
    }

    private fun decodeNeighbors(neighborData: String): MutableList<Int> {
        return try {
            Json.decodeFromString(neighborData)
        }
        catch (e: SerializationException) {
            mutableListOf()
        }
    }

    private companion object {
        const val TAG = "WebRepository"
    }
}