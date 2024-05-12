//package com.example.taskmanagementapp
//
//import android.view.LayoutInflater
//import android.widget.EditText
//import androidx.appcompat.app.AlertDialog
//import com.example.taskmanagementapp.Database.Entities.taskEntities
//import com.example.taskmanagementapp.Database.Repository.TodoRepository
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class dialogs {
//    private lateinit var adapter:toDoAdapter
//    private lateinit var viewModel:TaskActivityData
//    fun displayDialogForUpdate(repository: TodoRepository, task: taskEntities) {
//        val builder = AlertDialog.Builder(this)
//        val inflater = LayoutInflater.from(this)
//        val dialogView = inflater.inflate(R.layout.dialog, null)
//
//        // Find views in the custom layout
//        val taskInput = dialogView.findViewById<EditText>(R.id.taskInput)
//        val datePicker = dialogView.findViewById<EditText>(R.id.datePicker)
//
//        // Pre-fill the EditText fields with the existing task information
//        taskInput.setText(task.task)
//        datePicker.setText(task.date.toString())
//
//        builder.setView(dialogView)
//            .setTitle("Update Todo item:")
//            .setPositiveButton("Update") { dialog, which ->
//                val updatedTask = taskInput.text.toString()
//                val updatedDate = datePicker.text.toString()
//
//                CoroutineScope(Dispatchers.IO).launch {
//                    repository.update(task.copy(task = updatedTask, date = updatedDate))
//                    val data = repository.getAllTodoItems()
//                    runOnUiThread {
//                        viewModel.setData(data)
//                    }
//                }
//            }
//            .setNegativeButton("Cancel") { dialog, which ->
//                dialog.cancel()
//            }
//
//        val alertDialog = builder.create()
//        alertDialog.show()
//    }
//
//}