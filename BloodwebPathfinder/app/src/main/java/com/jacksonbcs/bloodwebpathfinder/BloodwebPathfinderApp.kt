package com.jacksonbcs.bloodwebpathfinder

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import com.jacksonbcs.bloodwebpathfinder.model.repository.NodeFirestoreDao
import com.jacksonbcs.bloodwebpathfinder.model.repository.NodeRoomDatabase
import com.jacksonbcs.bloodwebpathfinder.model.repository.WebRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BloodwebPathfinderApp : Application() {
    // Scope of application; don't need to cancel because it is destroyed alongside process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Only create databases and repository when needed
    private val roomDatabase by lazy { NodeRoomDatabase.getDatabase(this, applicationScope) }
    private val firestoreDatabase by lazy {
        val firestore = Firebase.firestore
        firestore.firestoreSettings = firestoreSettings { isPersistenceEnabled = false }
        firestore
    }
    val repository by lazy {
        WebRepository(roomDatabase.nodeDao(), NodeFirestoreDao(firestoreDatabase))
    }
}