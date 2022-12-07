package com.jacksonbcs.bloodwebpathfinder.main

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jacksonbcs.bloodwebpathfinder.BloodwebPathfinderApp
import com.jacksonbcs.bloodwebpathfinder.R
import com.jacksonbcs.bloodwebpathfinder.databinding.ActivityMainBinding
import com.jacksonbcs.bloodwebpathfinder.main.utils.WebViewModelFactory

class MainActivity : AppCompatActivity() {

    private val webViewModel: WebViewModel by viewModels {
        WebViewModelFactory((application as BloodwebPathfinderApp).repository)
    }
    private val imageSelectionResultLauncher = getImageSelectionResultLauncher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // TODO: This 0.42 factor bad?
        webViewModel.webRadius = (getScreenWidth() * 0.42).toInt()
        binding.viewmodel = webViewModel
        binding.lifecycleOwner = this

        binding.pickImageFab.setOnClickListener{
            intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT

            imageSelectionResultLauncher.launch(intent)
        }
    }

    private fun getImageSelectionResultLauncher(): ActivityResultLauncher<Intent> {

        return registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // Handle case where user cancels image selection
            if (result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(
                    applicationContext,
                    "No image selected.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                val fileName = getImageFileName(result.data?.data?.lastPathSegment)
                if (fileName != null) {
                    // Refresh view-model state with corresponding Bloodweb
                    webViewModel.getBloodweb(fileName)
                }
                else {
                    Toast.makeText(
                        applicationContext,
                        "Failed to load image from device!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun getImageFileName(uri: String?): String? {
        // TODO: Could do something about the empty string here
        // TODO: This is not general! Only works on the emulator...
        return uri
            ?.split("/")?.last()
            ?.split(".")?.first()
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
        const val TAG = "MainActivity"
    }
}