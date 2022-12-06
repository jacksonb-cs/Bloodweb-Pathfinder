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
    @ColumnInfo(name = "neighbors") val neighbors: MutableList<Int>
) {

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

    enum class Type(val value: Int) {
        ITEM(0),
        ADDON(1),
        PERK(2),
        OFFERING(3);

        companion object {
            // Necessary to deserialize from Firebase
            fun fromInt(value: Int): Type {
                return values().first { it.value == value }
            }
        }
    }

    enum class Color(val cost: Int) {
        // Costs are derived from the game
        BROWN(2000),
        YELLOW(2500),
        GREEN(3250),
        PURPLE(4000),
        IRIDESCENT(5000);

        companion object {
            // Necessary to deserialize from Firebase
            fun fromInt(value: Int): Color {
                return values().first { it.cost == value }
            }
        }
    }
}
