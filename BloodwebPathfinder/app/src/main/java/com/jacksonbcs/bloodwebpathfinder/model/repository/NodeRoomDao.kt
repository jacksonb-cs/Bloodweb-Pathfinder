package com.jacksonbcs.bloodwebpathfinder.model.repository

import androidx.room.*
import com.jacksonbcs.bloodwebpathfinder.model.Node
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeRoomDao {

    @Query("SELECT * FROM node_table")
    fun getAllNodes(): Flow<List<Node>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(node: Node)

    @Update
    suspend fun update(node: Node)

    @Query("DELETE FROM node_table")
    suspend fun deleteAll()
}