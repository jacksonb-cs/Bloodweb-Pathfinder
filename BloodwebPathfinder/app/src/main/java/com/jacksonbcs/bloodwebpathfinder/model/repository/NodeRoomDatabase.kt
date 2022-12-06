package com.jacksonbcs.bloodwebpathfinder.model.repository

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jacksonbcs.bloodwebpathfinder.model.AdjacencyListConverter
import com.jacksonbcs.bloodwebpathfinder.model.Node
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Node::class], version = 1, exportSchema = false)
@TypeConverters(AdjacencyListConverter::class)
abstract class NodeRoomDatabase : RoomDatabase() {

    abstract fun nodeDao(): NodeRoomDao

    companion object {
        // Singleton pattern
        @Volatile
        private var INSTANCE: NodeRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NodeRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NodeRoomDatabase::class.java,
                    "node_database"
                )
                    // Can add a callback here to do something on DB creation
                    .addCallback(NodeDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }

        private class NodeDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { nodeRoomDatabase ->
                    scope.launch {
                        populateDatabase(nodeRoomDatabase.nodeDao())
                    }
                }
            }

            private suspend fun populateDatabase(nodeDao: NodeRoomDao) {

                nodeDao.deleteAll()

                // Add sample nodes
                nodeDao.insert(
                    Node(0, 0, Node.Type.ADDON, Node.Color.BROWN, mutableListOf(2, 4))
                )
                nodeDao.insert(
                    Node(0, 1, Node.Type.ADDON, Node.Color.YELLOW, mutableListOf(2))
                )
                nodeDao.insert(
                    Node(1, 0, Node.Type.OFFERING, Node.Color.PURPLE, mutableListOf())
                )
                nodeDao.insert(
                    Node(1, 2, Node.Type.ITEM, Node.Color.GREEN, mutableListOf())
                )
                nodeDao.insert(
                    Node(2, 2, Node.Type.PERK, Node.Color.YELLOW, mutableListOf())
                )
            }
        }
    }
}