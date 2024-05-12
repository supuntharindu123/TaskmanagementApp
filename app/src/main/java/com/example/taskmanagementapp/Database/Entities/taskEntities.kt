package com.example.taskmanagementapp.Database.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class taskEntities(
    var task:String,
    var date: String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
