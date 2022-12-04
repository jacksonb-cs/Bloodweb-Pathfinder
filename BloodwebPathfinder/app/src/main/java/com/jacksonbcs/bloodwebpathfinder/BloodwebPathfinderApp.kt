package com.jacksonbcs.bloodwebpathfinder

import android.app.Application
import com.jacksonbcs.bloodwebpathfinder.model.repository.NodeRoomDatabase
import com.jacksonbcs.bloodwebpathfinder.model.repository.WebRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BloodwebPathfinderApp : Application() {
    // Scope of application; don't need to cancel because it is destroyed alongside process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Only create database when needed
    private val roomDatabase by lazy { NodeRoomDatabase.getDatabase(this) }
    // TODO: private val firestoreDatabase by lazy {  }
    val repository by lazy {
        WebRepository(roomDatabase.nodeDao() /*TODO: Pass FirestoreDao*/)
    }
}