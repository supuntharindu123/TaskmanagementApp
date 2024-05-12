package com.example.taskmanagementapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagementapp.Database.Entities.taskEntities
import com.example.taskmanagementapp.Database.Repository.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class toDoAdapter(tasks:List<taskEntities>,repository: TodoRepository,viewmodel:TaskActivityData): RecyclerView.Adapter<TodoViewHolder>() {
    var context: Context? = null
    val tasks = tasks
    val repository = repository
    val viewmodel=viewmodel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.tast_list,parent,false)
        context=parent.context
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.cbTodo.text=tasks.get(position).task
        holder.textdate.text= tasks.get(position).date.toString()
        holder.ivDelete.setOnClickListener{
            val ischecked=holder.cbTodo.isChecked
            if(ischecked){
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(tasks.get(position))
                    val data=repository.getAllTodoItems()
                    withContext(Dispatchers.Main) {
                        viewmodel.setData(data)
                    }
                }
                Toast.makeText(context,"Item deleted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Select to Item deleted", Toast.LENGTH_SHORT).show()
            }
        }
        holder.editbtn.setOnClickListener{
            val ischecked=holder.cbTodo.isChecked
            if(ischecked){
                CoroutineScope(Dispatchers.IO).launch {
                    repository.update(tasks.get(position))
                    val data=repository.getAllTodoItems()
                    withContext(Dispatchers.Main) {
                        viewmodel.setData(data)
                    }
                }
                Toast.makeText(context,"Item Update", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"Select to Item deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

}
class TodoViewHolder(view: View): RecyclerView.ViewHolder(view){
    val cbTodo: CheckBox
    val ivDelete: ImageView
    val textdate:TextView
    val editbtn:ImageView
    init {
        cbTodo = view.findViewById(R.id.cb)
        ivDelete = view.findViewById(R.id.iVdelete)
        textdate=view.findViewById(R.id.datetext)
        editbtn=view.findViewById(R.id.editbtn)
    }

}
