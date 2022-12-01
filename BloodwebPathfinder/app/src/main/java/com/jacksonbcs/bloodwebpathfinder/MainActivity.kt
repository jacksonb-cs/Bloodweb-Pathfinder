package com.jacksonbcs.bloodwebpathfinder

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import com.jacksonbcs.bloodwebpathfinder.databinding.ActivityMainBinding
import com.jacksonbcs.bloodwebpathfinder.model.Node
import com.jacksonbcs.bloodwebpathfinder.model.Vertex
import com.jacksonbcs.bloodwebpathfinder.model.Web
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    // Used for programmatic access to views
    private lateinit var binding: ActivityMainBinding

    // Map of coordinates to node view
    private var bloodweb = Web(WEB_CAPACITY)

    // Width of screen; used to calculate placement of web nodes
    private var sWidth by Delegates.notNull<Int>()

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
        sWidth = getScreenWidth()

        // Initialize the first (innermost) ring
        // TODO: This is just example junk
        val v = Vertex(
            Node(1, 0, null, null),
            (sWidth * 0.4).toInt()
        )
        val node = ImageView(this)
        node.x = v.xPos.toFloat()
        node.y = v.yPos.toFloat()
        node.setImageDrawable(resources.getDrawable(R.drawable.center_node))

        // TODO

        // Initialize the second (middle) ring
        // TODO

        // Initialize the third (outermost) ring
        // TODO
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