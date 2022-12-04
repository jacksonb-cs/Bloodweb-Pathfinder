package com.jacksonbcs.bloodwebpathfinder.model.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jacksonbcs.bloodwebpathfinder.model.Node

@Database(entities = [Node::class], version = 1, exportSchema = false)
abstract class NodeRoomDatabase : RoomDatabase() {

    abstract fun nodeDao(): NodeRoomDao

    companion object {
        // Singleton pattern
        @Volatile
        private var INSTANCE: NodeRoomDatabase? = null

        fun getDatabase(context: Context): NodeRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NodeRoomDatabase::class.java,
                    "node_database"
                )
                    // Can add a callback here to do something on DB creation
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}