package com.jacksonbcs.bloodwebpathfinder.main.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jacksonbcs.bloodwebpathfinder.main.WebViewModel
import com.jacksonbcs.bloodwebpathfinder.model.repository.WebRepository
import java.lang.IllegalArgumentException

class WebViewModelFactory(private val repository: WebRepository) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WebViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WebViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class!")
    }
}