package com.jacksonbcs.bloodwebpathfinder.model

import androidx.lifecycle.LiveData

data class Web(
    val vertices: LiveData<HashMap<Pair<Int, Int>, Vertex>>,
    // Triple<ring, position, neighbor_sequence> -> Edge
    val edges: LiveData<List<Pair<Vertex, Int>>>) {
    /* TODO:
     *  The whole "neighbor_sequence" concept seems a little arbitrary.
     *  I think that it will make the code more efficient, but the weirdness
     *  it introduces may not be worth it...
     *  It is certainly a thing I would change if I had more time!
     */
}