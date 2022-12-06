package com.jacksonbcs.bloodwebpathfinder.model.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NodeFirestoreDao(private val db: FirebaseFirestore) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun loadWebByFilename(
        filename: String,
        webLoadedCallback: suspend (nodeData: Map<String, Any>) -> Unit
    ) {

        db.collection(filename).get().addOnSuccessListener { collection ->

                CoroutineScope(Dispatchers.IO).launch {
                    for (doc in collection) {
                        webLoadedCallback(doc.data)
                    }
                }
        }
    }

    private companion object {
        const val TAG = "NodeFirestoreDao"
    }
}