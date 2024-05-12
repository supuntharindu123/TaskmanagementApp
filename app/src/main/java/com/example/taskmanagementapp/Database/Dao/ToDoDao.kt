package com.example.taskmanagementapp.Database.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskmanagementapp.Database.Entities.taskEntities

@Dao
interface ToDoDao {
    @Insert
    suspend fun insertToDo(todo:taskEntities)

    @Delete
    suspend fun deleteToDo(todo:taskEntities)

    @Update
    suspend fun updateToDo(todo: taskEntities)

    @Query("SELECT*FROM taskEntities")
    fun getAllToDo():List<taskEntities>
}