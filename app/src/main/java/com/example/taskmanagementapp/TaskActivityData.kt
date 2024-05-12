package com.example.taskmanagementapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskmanagementapp.Database.Entities.taskEntities

class TaskActivityData : ViewModel() {
    private val _data = MutableLiveData<List<taskEntities>>()
    val data: LiveData<List<taskEntities>> = _data
    fun setData(data:List<taskEntities>){
        _data.value = data
    }
}