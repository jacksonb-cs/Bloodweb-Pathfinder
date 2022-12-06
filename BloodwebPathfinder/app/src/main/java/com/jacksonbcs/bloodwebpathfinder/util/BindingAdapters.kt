package com.jacksonbcs.bloodwebpathfinder.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.jacksonbcs.bloodwebpathfinder.R
import com.jacksonbcs.bloodwebpathfinder.main.utils.EdgesView
import com.jacksonbcs.bloodwebpathfinder.model.Node
import com.jacksonbcs.bloodwebpathfinder.model.Vertex

@BindingAdapter("app:associatedVertex")
fun setVertex(view: ImageView, vertex: Vertex?) {

    // Set vertex position
    view.translationX = 0F
    view.translationY = 0F
    view.x += (vertex?.xPos ?: 0.0.toFloat())
    view.y += (vertex?.yPos ?: 0.0.toFloat())

    // Set display properties
    val vertexIcon = getNodeIcon(vertex?.node)
    if (vertexIcon != null) {
        view.setImageDrawable(ContextCompat.getDrawable(view.context, vertexIcon))
        view.visibility = View.VISIBLE
    }
    else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:webEdges"])
fun drawEdges(view: EdgesView, edges: List<Pair<Vertex, Int>>?) {

    // TODO
    //  Going to have to convert all of those individual vertices in viewmodel
    //  to something that makes a bit more sense!
    //  That'll change `setVertex` from above, as well.
}

// Returns null if the node, its color, or its type are null
fun getNodeIcon(node: Node?): Int? {
    // First determine item type
    return when (node?.type) {
        Node.Type.ADDON -> getAddonIcon(node.color)
        Node.Type.ITEM -> getItemIcon(node.color)
        Node.Type.PERK -> getPerkIcon(node.color)
        Node.Type.OFFERING -> getOfferingIcon(node.color)
        else -> null
    }
}

fun getAddonIcon(color: Node.Color?): Int? {
    // Addons can be any color except iridescent
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_addon_active
        Node.Color.YELLOW -> R.drawable.yellow_addon_active
        Node.Color.GREEN -> R.drawable.green_addon_active
        Node.Color.PURPLE -> R.drawable.purple_addon_active
        else -> null
    }
}

fun getItemIcon(color: Node.Color?): Int? {
    // Items can be any color
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_item_active
        Node.Color.YELLOW -> R.drawable.yellow_item_active
        Node.Color.GREEN -> R.drawable.green_item_active
        Node.Color.PURPLE -> R.drawable.purple_item_active
        Node.Color.IRIDESCENT -> R.drawable.iridescent_item_active
        else -> null
    }
}

fun getPerkIcon(color: Node.Color?): Int? {
    // Perks can only be YELLOW, GREEN, or PURPLE
    return when (color) {
        Node.Color.YELLOW -> R.drawable.yellow_perk_active
        Node.Color.GREEN -> R.drawable.green_perk_active
        Node.Color.PURPLE -> R.drawable.purple_perk_active
        else -> null
    }
}

fun getOfferingIcon(color: Node.Color?): Int? {
    // Offerings can be any color
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_offering_active
        Node.Color.YELLOW -> R.drawable.yellow_offering_active
        Node.Color.GREEN -> R.drawable.green_offering_active
        Node.Color.PURPLE -> R.drawable.purple_offering_active
        Node.Color.IRIDESCENT -> R.drawable.iridescent_offering_active
        else -> null
    }
}