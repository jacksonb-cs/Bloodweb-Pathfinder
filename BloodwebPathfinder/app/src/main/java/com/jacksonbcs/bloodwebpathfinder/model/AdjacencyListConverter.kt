package com.jacksonbcs.bloodwebpathfinder.model

import androidx.room.TypeConverter

class AdjacencyListConverter {

    @TypeConverter
    fun serializeAdjacencyList(list: MutableList<Pair<Int, Int>>): String {
        // TODO
        return ""
    }

    @TypeConverter
    fun deserializeAdjacencyList(data: String): MutableList<Pair<Int, Int>> {
        // TODO
        return mutableListOf()
    }
}