package com.jacksonbcs.bloodwebpathfinder.model

/*
 * A node in the Bloodweb.
 *
 * Defined by an ordered pair, <ring, position>
 *   - The innermost ring is 0, and the outermost is 2.
 *   - The count for position progresses clockwise, beginning at noon, at 0.
 *
 * Every node, if it exists, in ring 0 is connected to the center point.
 */
class Node(ring: Int, position: Int) {

    val ring: Int
    val position: Int

    init {
        if (paramsAreValid(ring, position)) {
            this.ring = ring
            this.position = position
        }
        else
            // TODO: Is this the best thing to do?
            throw IllegalArgumentException()
    }

    private fun paramsAreValid(ring: Int, position: Int): Boolean {

        // Ensure valid ring
        if (ring !in 0..2)
            return false

        return when (ring) {
            0 -> position in 0..5
            1 -> position in 0..11
            2 -> position in 0..11
            else -> false
        }
    }
}