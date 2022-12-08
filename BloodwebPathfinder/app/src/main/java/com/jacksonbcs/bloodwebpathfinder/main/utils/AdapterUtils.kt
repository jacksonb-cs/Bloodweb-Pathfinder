package com.jacksonbcs.bloodwebpathfinder.main.utils

import com.jacksonbcs.bloodwebpathfinder.R
import com.jacksonbcs.bloodwebpathfinder.model.Node

/* TODO:
 *  Listen, I'm out of time, and I can't think of a good way to use arrays
 *  since turning this whole thing into an object is messing things up.
 *  As I'm typing this, I've realized what the problem is. But again, I don't have time.
 *
 * TODO:
 *  Update: Now, it would be even easier to make the switch, but I'm in too deep for the night.
 */

// ========== INACTIVE ========== \\

fun getInactiveNodeIcon(node: Node?): Int? {
    // First determine item type
    return when (node?.type) {
        Node.Type.ADDON -> getInactiveAddonIcon(node.color)
        Node.Type.ITEM -> getInactiveItemIcon(node.color)
        Node.Type.PERK -> getInactivePerkIcon(node.color)
        Node.Type.OFFERING -> getInactiveOfferingIcon(node.color)
        else -> null
    }
}

fun getInactiveAddonIcon(color: Node.Color?): Int? {
    // Addons can be any color except iridescent
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_addon_inactive
        Node.Color.YELLOW -> R.drawable.yellow_addon_inactive
        Node.Color.GREEN -> R.drawable.green_addon_inactive
        Node.Color.PURPLE -> R.drawable.purple_addon_inactive
        else -> null
    }
}

fun getInactiveItemIcon(color: Node.Color?): Int? {
    // Items can be any color
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_item_inactive
        Node.Color.YELLOW -> R.drawable.yellow_item_inactive
        Node.Color.GREEN -> R.drawable.green_item_inactive
        Node.Color.PURPLE -> R.drawable.purple_item_inactive
        Node.Color.IRIDESCENT -> R.drawable.iridescent_item_inactive
        else -> null
    }
}

fun getInactivePerkIcon(color: Node.Color?): Int? {
    // Perks can only be YELLOW, GREEN, or PURPLE
    return when (color) {
        Node.Color.YELLOW -> R.drawable.yellow_perk_inactive
        Node.Color.GREEN -> R.drawable.green_perk_inactive
        Node.Color.PURPLE -> R.drawable.purple_perk_inactive
        else -> null
    }
}

fun getInactiveOfferingIcon(color: Node.Color?): Int? {
    // Offerings can be any color
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_offering_inactive
        Node.Color.YELLOW -> R.drawable.yellow_offering_inactive
        Node.Color.GREEN -> R.drawable.green_offering_inactive
        Node.Color.PURPLE -> R.drawable.purple_offering_inactive
        Node.Color.IRIDESCENT -> R.drawable.iridescent_offering_inactive
        else -> null
    }
}

// ========== ACTIVE ========== \\

// Returns null if the node, its color, or its type are null
fun getActiveNodeIcon(node: Node?): Int? {
    // First determine item type
    return when (node?.type) {
        Node.Type.ADDON -> getActiveAddonIcon(node.color)
        Node.Type.ITEM -> getActiveItemIcon(node.color)
        Node.Type.PERK -> getActivePerkIcon(node.color)
        Node.Type.OFFERING -> getActiveOfferingIcon(node.color)
        else -> null
    }
}

fun getActiveAddonIcon(color: Node.Color?): Int? {
    // Addons can be any color except iridescent
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_addon_active
        Node.Color.YELLOW -> R.drawable.yellow_addon_active
        Node.Color.GREEN -> R.drawable.green_addon_active
        Node.Color.PURPLE -> R.drawable.purple_addon_active
        else -> null
    }
}

fun getActiveItemIcon(color: Node.Color?): Int? {
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

fun getActivePerkIcon(color: Node.Color?): Int? {
    // Perks can only be YELLOW, GREEN, or PURPLE
    return when (color) {
        Node.Color.YELLOW -> R.drawable.yellow_perk_active
        Node.Color.GREEN -> R.drawable.green_perk_active
        Node.Color.PURPLE -> R.drawable.purple_perk_active
        else -> null
    }
}

fun getActiveOfferingIcon(color: Node.Color?): Int? {
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

// ========== BOUGHT ========== \\
// TODO: Get rid of all of that extra whitespace at the bottom (VS Code has spoiled me)

// Returns null if the node, its color, or its type are null
fun getBoughtNodeIcon(node: Node?): Int? {
    // First determine item type
    return when (node?.type) {
        Node.Type.ADDON -> getBoughtAddonIcon(node.color)
        Node.Type.ITEM -> getBoughtItemIcon(node.color)
        Node.Type.PERK -> getBoughtPerkIcon(node.color)
        Node.Type.OFFERING -> getBoughtOfferingIcon(node.color)
        else -> null
    }
}

fun getBoughtAddonIcon(color: Node.Color?): Int? {
    // Addons can be any color except iridescent
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_addon_bought
        Node.Color.YELLOW -> R.drawable.yellow_addon_bought
        Node.Color.GREEN -> R.drawable.green_addon_bought
        Node.Color.PURPLE -> R.drawable.purple_addon_bought
        else -> null
    }
}

fun getBoughtItemIcon(color: Node.Color?): Int? {
    // Items can be any color
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_item_bought
        Node.Color.YELLOW -> R.drawable.yellow_item_bought
        Node.Color.GREEN -> R.drawable.green_item_bought
        Node.Color.PURPLE -> R.drawable.purple_item_bought
        Node.Color.IRIDESCENT -> R.drawable.iridescent_item_bought
        else -> null
    }
}

fun getBoughtPerkIcon(color: Node.Color?): Int? {
    // Perks can only be YELLOW, GREEN, or PURPLE
    return when (color) {
        Node.Color.YELLOW -> R.drawable.yellow_perk_bought
        Node.Color.GREEN -> R.drawable.green_perk_bought
        Node.Color.PURPLE -> R.drawable.purple_perk_bought
        else -> null
    }
}

fun getBoughtOfferingIcon(color: Node.Color?): Int? {
    // Offerings can be any color
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_offering_bought
        Node.Color.YELLOW -> R.drawable.yellow_offering_bought
        Node.Color.GREEN -> R.drawable.green_offering_bought
        Node.Color.PURPLE -> R.drawable.purple_offering_bought
        Node.Color.IRIDESCENT -> R.drawable.iridescent_offering_bought
        else -> null
    }
}

// ========== CONSUMED ========== \\

// Returns null if the node, its color, or its type are null
fun getConsumedNodeIcon(node: Node?): Int? {
    // First determine item type
    return when (node?.type) {
        Node.Type.ADDON -> getConsumedAddonIcon(node.color)
        Node.Type.ITEM -> getConsumedItemIcon(node.color)
        Node.Type.PERK -> getConsumedPerkIcon(node.color)
        Node.Type.OFFERING -> getConsumedOfferingIcon(node.color)
        else -> null
    }
}

fun getConsumedAddonIcon(color: Node.Color?): Int? {
    // Addons can be any color except iridescent
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_addon_consumed
        Node.Color.YELLOW -> R.drawable.yellow_addon_consumed
        Node.Color.GREEN -> R.drawable.green_addon_consumed
        Node.Color.PURPLE -> R.drawable.purple_addon_consumed
        else -> null
    }
}

fun getConsumedItemIcon(color: Node.Color?): Int? {
    // Items can be any color
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_item_consumed
        Node.Color.YELLOW -> R.drawable.yellow_item_consumed
        Node.Color.GREEN -> R.drawable.green_item_consumed
        Node.Color.PURPLE -> R.drawable.purple_item_consumed
        Node.Color.IRIDESCENT -> R.drawable.iridescent_item_consumed
        else -> null
    }
}

fun getConsumedPerkIcon(color: Node.Color?): Int? {
    // Perks can only be YELLOW, GREEN, or PURPLE
    return when (color) {
        Node.Color.YELLOW -> R.drawable.yellow_perk_consumed
        Node.Color.GREEN -> R.drawable.green_perk_consumed
        Node.Color.PURPLE -> R.drawable.purple_perk_consumed
        else -> null
    }
}

fun getConsumedOfferingIcon(color: Node.Color?): Int? {
    // Offerings can be any color
    return when (color) {
        Node.Color.BROWN -> R.drawable.brown_offering_consumed
        Node.Color.YELLOW -> R.drawable.yellow_offering_consumed
        Node.Color.GREEN -> R.drawable.green_offering_consumed
        Node.Color.PURPLE -> R.drawable.purple_offering_consumed
        Node.Color.IRIDESCENT -> R.drawable.iridescent_offering_consumed
        else -> null
    }
}