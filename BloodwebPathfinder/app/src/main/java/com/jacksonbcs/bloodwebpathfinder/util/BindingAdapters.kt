package com.jacksonbcs.bloodwebpathfinder.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.jacksonbcs.bloodwebpathfinder.model.Node
import com.jacksonbcs.bloodwebpathfinder.model.Vertex

// TODO: Remove
@BindingAdapter(value = ["app:nodeRing", "app:nodePosition"], requireAll = true)
fun getPositionX(nodeView: View, ring: Int, position: Int) {
//
//            val vertex = web.value?.get(Pair(ring, position))
//    val vertex: Vertex? = null
//
//    if (vertex != null) {
//        nodeView.x = vertex.xPos.toFloat()
//    }
//    else {
//        nodeView.visibility = View.GONE
//    }
}

@BindingAdapter("app:associatedVertex")
fun setVertex(nodeView: View, vertex: Vertex) {
    // TODO: Remove!
    val tag = "BindingAdapter"
    Log.d(tag, "====================")
    Log.d(tag, "Ring: ${vertex.node.ring}, Position: ${vertex.node.position}")
    Log.d(tag, "X: ${vertex.xPos}, Y: ${vertex.yPos}")

    nodeView.x = vertex.xPos
    nodeView.y = vertex.yPos
}