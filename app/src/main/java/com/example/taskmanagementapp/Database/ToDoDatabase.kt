package com.example.taskmanagementapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmanagementapp.Database.Dao.ToDoDao
import com.example.taskmanagementapp.Database.Entities.taskEntities

@Database(entities = [taskEntities::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun getToDo(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getInstance(context: Context): ToDoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "task_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}