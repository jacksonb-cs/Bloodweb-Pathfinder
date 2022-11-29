package com.jacksonbcs.bloodwebpathfinder.model

class Web {

    private val maxNodes = 30

    val nodes = HashMap<Pair<Int, Int>, Node>(maxNodes)
}