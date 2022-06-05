package com.example.taskfrog.ui.list

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.databinding.FragmentTaskBinding
import com.example.taskfrog.room.FrogTask
import com.example.taskfrog.room.FrogTaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

//TODO Back Button and Title of Fragment

class TaskFragment : Fragment() {
    private lateinit var taskFab : FloatingActionButton
    private lateinit var taskRecyclerView : RecyclerView
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var mFrogTaskViewModel : FrogTaskViewModel
    private var _binding : FragmentTaskBinding? = null
    private lateinit var date : String
    var tempListId: Int = ListFragment.frogListId
    var cal = Calendar.getInstance()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(taskItemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(taskItemView, savedInstanceState)
        taskFab = view?.findViewById(R.id.task_fab)!!
        taskRecyclerView = view?.findViewById(R.id.taskRecyclerView)!!
        taskAdapter = TaskAdapter(this.requireContext(),this)
        taskRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        taskRecyclerView.adapter = taskAdapter
        mFrogTaskViewModel = ViewModelProvider(this)[FrogTaskViewModel::class.java]
        mFrogTaskViewModel.lateInitialize(tempListId)
        mFrogTaskViewModel.getAllTasks?.observe(viewLifecycleOwner) {
                frogTasks -> taskAdapter.setFrogTasks(frogTasks)
        }
        taskFab.setOnClickListener{
            addTask()
        }
    }

    private fun addTask() {
        val inflater = LayoutInflater.from(this.requireContext())
        val v = inflater.inflate(R.layout.add_task, null)
        val taskName = v.findViewById<EditText>(R.id.taskName)
        val dueDate = v.findViewById<Button>(R.id.taskDate)
        val description = v.findViewById<EditText>(R.id.taskDescription)
        val addDialog = AlertDialog.Builder(this.requireContext())

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        dueDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                DatePickerDialog(this@TaskFragment.requireContext(), dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            val name = taskName.text.toString()
            val description = description.text.toString()
            val frogTask = FrogTask(null, name, description, date, tempListId) //TODO: Did this prematurely
            mFrogTaskViewModel.addFrogTask(frogTask)
            taskAdapter.notifyDataSetChanged()
            Toast.makeText(this.requireContext(), "Adding Task Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this.requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.GERMAN)
        date = sdf.format(cal.getTime())
    }

    fun setListId(listId: Int){
        this.tempListId = listId
    }
}