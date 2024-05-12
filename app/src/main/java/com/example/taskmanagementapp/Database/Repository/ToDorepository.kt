package com.example.taskmanagementapp.Database.Repository

import com.example.taskmanagementapp.Database.Entities.taskEntities
import com.example.taskmanagementapp.Database.ToDoDatabase

class TodoRepository(
    private val db:ToDoDatabase
) {
    suspend fun insert(todo:taskEntities) = db.getToDo().insertToDo(todo)
    suspend fun delete(todo:taskEntities) = db.getToDo().deleteToDo(todo)
    suspend fun update(todo: taskEntities)=db.getToDo().updateToDo(todo)
    fun getAllTodoItems():List<taskEntities> = db.getToDo().getAllToDo()
}