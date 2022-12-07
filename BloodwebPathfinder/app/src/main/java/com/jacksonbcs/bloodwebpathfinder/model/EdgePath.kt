package com.jacksonbcs.bloodwebpathfinder.model

data class EdgePath(
    val srcX: Float,
    val srcY: Float,
    val destX: Float,
    val destY: Float,
    val type: EdgeType
) {

    enum class EdgeType {
        INACTIVE,   // Unreachable by player
        ACTIVE,     // Reachable by player
        BOUGHT,     // Bought
        CONSUMED    // Destroyed by the Entity (antagonist)
    }
}