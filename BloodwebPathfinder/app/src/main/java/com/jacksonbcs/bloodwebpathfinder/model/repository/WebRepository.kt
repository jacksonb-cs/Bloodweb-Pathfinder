package com.jacksonbcs.bloodwebpathfinder.model.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jacksonbcs.bloodwebpathfinder.main.WebViewModel
import com.jacksonbcs.bloodwebpathfinder.model.Node
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException

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

    // TODO: Firestore calls
}