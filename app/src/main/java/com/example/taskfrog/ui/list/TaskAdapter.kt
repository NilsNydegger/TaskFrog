package com.example.taskfrog.ui.list

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import java.text.DateFormat
import kotlin.collections.ArrayList

class TaskAdapter(val c: Context, val mTask: ArrayList<TaskData>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

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
                        val date = vTask.findViewById<EditText>(R.id.taskDate)
                        val description = vTask.findViewById<EditText>(R.id.taskDescription)
                            AlertDialog.Builder(c)
                                .setView(vTask)
                                .setPositiveButton("Ok") {
                                    dialog,_->
                                    position.name = name.text.toString()
                                    position.dueDate = DateFormat.getDateInstance().format(date)
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

}