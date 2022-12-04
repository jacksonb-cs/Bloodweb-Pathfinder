package com.jacksonbcs.bloodwebpathfinder.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jacksonbcs.bloodwebpathfinder.BloodwebPathfinder
import com.jacksonbcs.bloodwebpathfinder.R
import com.jacksonbcs.bloodwebpathfinder.databinding.ActivityMainBinding
import com.jacksonbcs.bloodwebpathfinder.model.Node
import com.jacksonbcs.bloodwebpathfinder.model.Vertex
import com.jacksonbcs.bloodwebpathfinder.model.Web
import com.jacksonbcs.bloodwebpathfinder.repository.WebViewModelFactory

class MainActivity : AppCompatActivity() {

    private val webViewModel: WebViewModel by viewModels {
        WebViewModelFactory((application as BloodwebPathfinder).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewmodel = webViewModel

        // Set position of every vertex (bloodweb node) according to screen size
        // TODO: This will become obsolete!
//        initializeWeb()
    }

    // TODO: Fix up because of sweeping changes in application structure (or maybe this dies?)
    // Set position of every vertex (bloodweb node) according to screen size
//    private fun initializeWeb() {
//
//        val nodeList = getNodeList()
//        val webRadius = (getScreenWidth() * 0.4).toInt()
//
//        for ((i, node) in nodeList.withIndex()) {
//
//            // Deal with first ring (only has 6 nodes)
//            if (i < 7) {    // TODO: Hardcoded values?
//                val vertex = Vertex(
//                    Node(0, i, null, null),
//                    webRadius
//                )
//                node.x = vertex.xPos.toFloat()
//                node.y = vertex.yPos.toFloat()
//            }
//        }
//    }

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