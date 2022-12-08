package com.jacksonbcs.bloodwebpathfinder.model.utils

import com.jacksonbcs.bloodwebpathfinder.model.Node

data class EdgePath(
    val srcX: Float,
    val srcY: Float,
    val destX: Float,
    val destY: Float,
    val type: Node.State
) {

}