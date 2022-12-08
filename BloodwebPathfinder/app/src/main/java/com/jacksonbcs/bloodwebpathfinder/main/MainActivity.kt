package com.jacksonbcs.bloodwebpathfinder.main

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jacksonbcs.bloodwebpathfinder.BloodwebPathfinderApp
import com.jacksonbcs.bloodwebpathfinder.R
import com.jacksonbcs.bloodwebpathfinder.databinding.ActivityMainBinding
import com.jacksonbcs.bloodwebpathfinder.main.simulation.Adversary
import com.jacksonbcs.bloodwebpathfinder.main.simulation.Player
import com.jacksonbcs.bloodwebpathfinder.main.simulation.Simulation
import com.jacksonbcs.bloodwebpathfinder.main.utils.WebViewModelFactory
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val webViewModel: WebViewModel by viewModels {
        WebViewModelFactory((application as BloodwebPathfinderApp).repository)
    }

    // Handles user image selection
    private val imageSelectionResultLauncher = getImageSelectionResultLauncher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        webViewModel.webRadius = (getScreenWidth() * webRadiusScale).toInt()
        binding.viewmodel = webViewModel
        binding.lifecycleOwner = this

        binding.pickImageFab.setOnClickListener{
            intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT

            imageSelectionResultLauncher.launch(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // TODO: Could provide option to change simulation speed
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Determine which item was selected
        return when (item.itemId) {
            R.id.start_simulation -> {
                startSimulation()
                true
            }
            R.id.settings -> { /* Absolutely nothing, tee-hee */ true}
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Run simulation on non-UI thread
    // TODO: Handle calling this method while a simulation is still/already running
    private fun startSimulation() {
        val coroutineDispatcher = Dispatchers.Default
        CoroutineScope(coroutineDispatcher).launch {
            Simulation(webViewModel, Simulation.SimulationSpeed.MEDIUM).start()
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

    private companion object {
        const val webRadiusScale = 0.42
        const val TAG = "MainActivity"
    }
}