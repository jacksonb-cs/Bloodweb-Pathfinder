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
import com.jacksonbcs.bloodwebpathfinder.model.repository.WebViewModelFactory

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

    // TODO: There must be a better way of doing this...
    // Putting it at the bottom because it is shameful
    /*
    private fun getNodeList(): List<ImageView> {
        return listOf(
            binding.node00,
            binding.node01,
            binding.node02,
            binding.node03,
            binding.node04,
            binding.node05,
            binding.node10,
            binding.node11,
            binding.node12,
            binding.node13,
            binding.node14,
            binding.node15,
            binding.node16,
            binding.node17,
            binding.node18,
            binding.node19,
            binding.node110,
            binding.node111,
            binding.node20,
            binding.node21,
            binding.node22,
            binding.node23,
            binding.node24,
            binding.node25,
            binding.node26,
            binding.node27,
            binding.node28,
            binding.node29,
            binding.node210,
            binding.node211
        )
    } */

    companion object {
        const val WEB_CAPACITY = 30

        const val TAG = "MainActivity"
    }
}