package com.example.taskfrog.ui.list

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TaskAdapter(val c: Context, val mTask: ArrayList<TaskData>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private lateinit var date : String
    var cal = Calendar.getInstance()

    inner class TaskViewHolder(vTask: View) : RecyclerView.ViewHolder(vTask) {
        var taskName: TextView
        var dueDate: TextView
        var description: TextView
        var mMenus: ImageView

        init {
            taskName = vTask.findViewById<TextView>(R.id.mTaskName)
            dueDate = vTask.findViewById<TextView>(R.id.mDate)
            description = vTask.findViewById<TextView>(R.id.mDescription)
            mMenus = vTask.findViewById(R.id.mMenus)
            mMenus.setOnClickListener {
                popupMenus(it)
            }
        }

        private fun popupMenus(v: View) {
            val position = mTask[adapterPosition]
            val popupMenus = PopupMenu(c, v)

            popupMenus.inflate(R.menu.show_menu_task)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editText -> {
                        val vTask = LayoutInflater.from(c).inflate(R.layout.add_task, null)
                        val name = vTask.findViewById<EditText>(R.id.taskName)
                        val dueDate = vTask.findViewById<Button>(R.id.taskDate)
                        val description = vTask.findViewById<EditText>(R.id.taskDescription)

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
                                DatePickerDialog(c, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
                            }
                        })

                            AlertDialog.Builder(c)
                                .setView(vTask)
                                .setPositiveButton("Ok") {
                                    dialog,_->
                                    position.name = name.text.toString()
                                    position.dueDate = date
                                    position.description = description.text.toString()
                                    notifyDataSetChanged()
                                    Toast.makeText(c, "Task Edited", Toast.LENGTH_SHORT).show()
                                    dialog.dismiss()

                                }
                                .setNegativeButton("Cancel") {
                                    dialog,_->
                                    dialog.dismiss()

                                }
                                .create()
                                .show()

                        true
                    }
                    R.id.delete -> {
                        /**set delete*/
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Are you sure delete this Task?")
                            .setPositiveButton("Yes") { dialog, _ ->
                                mTask.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c, "Deleted Task", Toast.LENGTH_SHORT)
                                    .show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else -> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vTask = inflater.inflate(R.layout.task_item,parent, false)
        return TaskViewHolder(vTask)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val newTask = mTask[position]
        holder.taskName.text = newTask.name
        holder.dueDate.text = newTask.dueDate
        holder.description.text = newTask.description
    }

    override fun getItemCount(): Int {
        return mTask.size
    }

    private fun updateDateInView() {
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.GERMAN)
        date = sdf.format(cal.getTime())
    }
}