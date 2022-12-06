package com.jacksonbcs.bloodwebpathfinder.model

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AdjacencyListConverter {

    @TypeConverter
    fun serializeAdjacencyList(list: MutableList<Int>): String {

        return Json.encodeToString(list)
    }

    @TypeConverter
    fun deserializeAdjacencyList(data: String): MutableList<Int> {

        return Json.decodeFromString(data)
    }
}