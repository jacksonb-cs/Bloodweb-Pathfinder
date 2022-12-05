package com.jacksonbcs.bloodwebpathfinder.model

import androidx.room.ColumnInfo
import androidx.room.Entity

/*
 * A node in the Bloodweb.
 *
 * Defined by an ordered pair, <ring, position>
 *   - The innermost ring is 0, and the outermost is 2.
 *   - The count for position progresses clockwise, beginning at noon, at 0.
 *
 * Every node, if it exists, in ring 0 is connected to the center point.
 */
@Entity(tableName = "node_table", primaryKeys = ["ring", "position"])
data class Node(
    val ring: Int,
    val position: Int,
    @ColumnInfo(name = "type") var type: Type?,
    @ColumnInfo(name = "color") var color: Color?,
    @ColumnInfo(name = "neighbors") val neighbors: MutableList<Pair<Int, Int>>
) {
    enum class Type {
        ITEM,
        ADDON,
        PERK,
        OFFERING
    }

    enum class Color {
        BROWN,
        YELLOW,
        GREEN,
        PURPLE,
        IRIDESCENT
    }
    // TODO: Explore using these as the type of the relevant parameters above
//    enum class Type(val type: String) {
//        ITEM("item"),
//        ADDON("addon"),
//        PERK("perk"),
//        OFFERING("offering")
//    }

//    enum class Color(val color: String) {
//        BROWN("brown"),
//        YELLOW("yellow"),
//        GREEN("green"),
//        PURPLE("purple"),
//        IRIDESCENT("iridescent")
//    }
fun cycleColor() {
    // Node type determines allowable colors, which changes the cycle behavior
    color = when (type) {
        Type.ADDON -> incrementAddonRarity()
        Type.ITEM -> incrementItemRarity()
        Type.PERK -> incrementPerkRarity()
        Type.OFFERING -> incrementOfferingRarity()
        else -> null
    }
}

    private fun incrementAddonRarity(): Color? {
        return when (color) {
            Color.BROWN -> Color.YELLOW
            Color.YELLOW -> Color.GREEN
            Color.GREEN -> Color.PURPLE
            Color.PURPLE -> Color.BROWN
            else -> null
        }
    }

    private fun incrementItemRarity(): Color? {
        return when (color) {
            Color.BROWN -> Color.YELLOW
            Color.YELLOW -> Color.GREEN
            Color.GREEN -> Color.PURPLE
            Color.PURPLE -> Color.IRIDESCENT
            Color.IRIDESCENT -> Color.BROWN
            else -> null
        }
    }

    private fun incrementPerkRarity(): Color? {
        return when (color) {
            Color.YELLOW -> Color.GREEN
            Color.GREEN -> Color.PURPLE
            Color.PURPLE -> Color.YELLOW
            else -> null
        }
    }

    private fun incrementOfferingRarity(): Color? {
        return when (color) {
            Color.BROWN -> Color.YELLOW
            Color.YELLOW -> Color.GREEN
            Color.GREEN -> Color.PURPLE
            Color.PURPLE -> Color.IRIDESCENT
            Color.IRIDESCENT -> Color.BROWN
            else -> null
        }
    }
}