package com.jacksonbcs.bloodwebpathfinder.model

class Web(numNodes: Int) {

    val nodes = HashMap<Pair<Int, Int>, Node>(numNodes)

//    fun getNode(ring: Int, position: Int): Node {
//
//        return nodes[Pair(ring, position)]
//    }
}