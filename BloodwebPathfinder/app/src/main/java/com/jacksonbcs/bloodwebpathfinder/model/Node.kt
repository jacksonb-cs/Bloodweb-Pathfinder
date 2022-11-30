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
data class Node(
    val ring: Int,
    val position: Int,
    val type: String?,
    val color: String?,
) {
    // TODO: Explore using these as the type of the relevant parameters above
    enum class Type(val type: String) {
        ITEM("item"),
        ADDON("addon"),
        PERK("perk"),
        OFFERING("offering")
    }

    enum class Color(val color: String) {
        BROWN("brown"),
        YELLOW("yellow"),
        GREEN("green"),
        PURPLE("purple"),
        IRIDESCENT("iridescent")
    }
}