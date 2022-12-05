package com.jacksonbcs.bloodwebpathfinder.model.repository

import androidx.annotation.WorkerThread
import com.google.firebase.firestore.FirebaseFirestore

class NodeFirestoreDao(private val db: FirebaseFirestore) {

    @WorkerThread
    suspend fun getWebByFilename(filename: String) {
        db.collection(filename)
    }
}