package com.jacksonbcs.bloodwebpathfinder.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jacksonbcs.bloodwebpathfinder.BloodwebPathfinderApp
import com.jacksonbcs.bloodwebpathfinder.R
import com.jacksonbcs.bloodwebpathfinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val webViewModel: WebViewModel by viewModels {
        WebViewModelFactory((application as BloodwebPathfinderApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        webViewModel.webRadius = (getScreenWidth() * 0.4).toInt()
        binding.viewmodel = webViewModel
        binding.lifecycleOwner = this
    }

    private fun getScreenWidth(): Int {

        val metrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            @Suppress("DEPRECATION")
            this.display?.getRealMetrics(metrics)
        }
        else {
            @Suppress("DEPRECATION")
            this.windowManager.defaultDisplay.getMetrics(metrics)
        }

        return metrics.widthPixels
    }

    companion object {
        const val WEB_CAPACITY = 30

        const val TAG = "MainActivity"
    }
}