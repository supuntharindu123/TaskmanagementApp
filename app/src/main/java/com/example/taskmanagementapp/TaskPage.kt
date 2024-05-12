package com.example.taskmanagementapp

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagementapp.Database.Entities.taskEntities
import com.example.taskmanagementapp.Database.Repository.TodoRepository
import com.example.taskmanagementapp.Database.ToDoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TaskPage : AppCompatActivity() {
    private lateinit var adapter:toDoAdapter
    private lateinit var viewModel:TaskActivityData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_page)

        val addbtn:ImageView =findViewById(R.id.addbtn)
        val recycle:RecyclerView=findViewById(R.id.recylcle)

        val repository = TodoRepository(ToDoDatabase.getInstance(this))
        viewModel = ViewModelProvider(this)[TaskActivityData::class.java]

//        val adapters=toDoAdapter()
//        list.adapter=adapters
//        list.layoutManager=LinearLayoutManager(this)

        viewModel.data.observe(this){
            adapter=toDoAdapter(it,repository,viewModel)
            recycle.adapter=adapter
            recycle.layoutManager= LinearLayoutManager(this)
        }
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllTodoItems()
            runOnUiThread {
                viewModel.setData(data)
            }
        }
        addbtn.setOnClickListener {
            displayDialog(repository)
        }

    }

    fun displayDialog(repository: TodoRepository) {
        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.dialog, null)

        // Find views in the custom layout
        val taskInput = dialogView.findViewById<EditText>(R.id.taskInput)
        val datePicker = dialogView.findViewById<DatePicker>(R.id.datePicker)

        builder.setView(dialogView)
            .setTitle("Enter New Task:")
            .setPositiveButton("OK") { dialog, which ->
                val task = taskInput.text.toString()
                val year = datePicker.year
                val month = datePicker.month
                val day = datePicker.dayOfMonth
                val calendar = Calendar.getInstance()
                calendar.set(year, month, day)

                val selectedDate = "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.DAY_OF_MONTH)}"

                CoroutineScope(Dispatchers.IO).launch {
                    repository.insert(taskEntities(task, selectedDate))
                    val data = repository.getAllTodoItems()
                    runOnUiThread {
                        viewModel.setData(data)
                    }
                }
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

        val alertDialog = builder.create()
        alertDialog.show()
    }


}