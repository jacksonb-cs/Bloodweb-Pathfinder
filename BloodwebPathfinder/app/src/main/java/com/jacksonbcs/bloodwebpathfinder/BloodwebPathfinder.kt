package com.jacksonbcs.bloodwebpathfinder

import android.app.Application
import com.jacksonbcs.bloodwebpathfinder.repository.WebRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BloodwebPathfinder : Application() {
    // Scope of application; don't need to cancel because it is destroyed alongside process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Only create database when needed
    // TODO: private val roomDatabase by lazy {  }
    // TODO: private val firestoreDatabase by lazy {  }
    val repository by lazy { WebRepository(/*TODO: Pass database instances!*/) }
}