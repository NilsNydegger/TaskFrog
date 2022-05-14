package com.example.taskfrog.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.databinding.ActivityTaskBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TaskActivity : AppCompatActivity() {

    private lateinit var taskFab : FloatingActionButton
    private lateinit var taskRecyclerView : RecyclerView
    private lateinit var taskList : ArrayList<TaskData>
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        taskList = ArrayList()

        taskFab = findViewById(R.id.task_fab)
        taskRecyclerView = findViewById(R.id.taskRecyclerView)

        taskAdapter = TaskAdapter(this,taskList)

        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.adapter = taskAdapter

        taskFab.setOnClickListener{ addTask() }
    }

    private fun addTask() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_task, null)
        val taskName = v.findViewById<EditText>(R.id.taskName)
        val dueDate = v.findViewById<EditText>(R.id.taskDate)
        val description = v.findViewById<EditText>(R.id.taskDescription)
        val addDialog = AlertDialog.Builder(this)
        val df = SimpleDateFormat("dd-MM-yyyy")

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog,_->
            val name = taskName.text.toString()
            val date = df.parse(dueDate.toString()) as Date
            val description = description.text.toString()
            taskList.add(TaskData(name,date,description))
            taskAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Adding Task Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}