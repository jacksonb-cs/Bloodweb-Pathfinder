package com.jacksonbcs.bloodwebpathfinder

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import com.jacksonbcs.bloodwebpathfinder.databinding.ActivityMainBinding
import com.jacksonbcs.bloodwebpathfinder.model.Node
import com.jacksonbcs.bloodwebpathfinder.model.Vertex
import com.jacksonbcs.bloodwebpathfinder.model.Web

class MainActivity : AppCompatActivity() {

    // Used for programmatic access to views
    private lateinit var binding: ActivityMainBinding

    // Map of coordinates to node view
    private var bloodweb = Web(WEB_CAPACITY)

    // Width of screen; used to calculate placement of web nodes
    // TODO: Determine if this is necessary
//    private var sWidth by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Decide whether to use this (fragment) or not
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            add<WebFragment>(R.id.web_container_view)
//        }

        // Using View Binding for easier layout management
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get width of screen
        // TODO: Part of determining if this as an attribute is necessary
//        sWidth = getScreenWidth()

        // Set position of every vertex (bloodweb node) according to screen size
        initializeWeb()

        // TODO: This is just example junk
        val v = Vertex(
            Node(1, 11, null, null),
            (getScreenWidth() * 0.4).toInt()
        )
        binding.node111.x = v.xPos.toFloat()
        binding.node111.y = v.yPos.toFloat()
        val v1 = Vertex(
            Node(2, 3, null, null),
            (getScreenWidth() * 0.4).toInt()
        )
        binding.node23.x = v1.xPos.toFloat()
        binding.node23.y = v1.yPos.toFloat()
    }

    // Set position of every vertex (bloodweb node) according to screen size
    private fun initializeWeb() {

        val nodeList = getNodeList()
        val webRadius = (getScreenWidth() * 0.4).toInt()

        for ((i, node) in nodeList.withIndex()) {

            // Deal with first ring (only has 6 nodes)
            if (i < 7) {    // TODO: Hardcoded values?
                val vertex = Vertex(
                    Node(0, i, null, null),
                    webRadius
                )
                node.x = vertex.xPos.toFloat()
                node.y = vertex.yPos.toFloat()
            }
        }
    }

    private fun initializeVertex(viewNode: ImageView, ring: Int, position: Int) {
        // TODO: Not yet implemented!
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
    }

    companion object {
        const val WEB_CAPACITY = 30

        const val TAG = "MainActivity"
    }
}