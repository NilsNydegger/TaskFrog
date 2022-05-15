package com.example.taskfrog.ui.list

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TaskActivity : AppCompatActivity() {

    private lateinit var taskFab : FloatingActionButton
    private lateinit var taskRecyclerView : RecyclerView
    private lateinit var taskList : ArrayList<TaskData>
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var date : String
    private var cal: Calendar = Calendar.getInstance()

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
        val dueDate = v.findViewById<Button>(R.id.taskDate)
        val description = v.findViewById<EditText>(R.id.taskDescription)
        val addDialog = AlertDialog.Builder(this)

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }

        dueDate!!.setOnClickListener {
            DatePickerDialog(
                this@TaskActivity,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
            dialog,_->
            val name = taskName.text.toString()
            val taskDescription = description.text.toString()
            taskList.add(TaskData(name,date,taskDescription))
            taskAdapter.notifyItemChanged(1)
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

    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.GERMAN)
        date = sdf.format(cal.time)
    }
}