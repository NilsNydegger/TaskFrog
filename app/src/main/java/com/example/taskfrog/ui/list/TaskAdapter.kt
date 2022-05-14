package com.example.taskfrog.ui.list

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TaskAdapter(val c: Context, val mTask: ArrayList<TaskData>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var taskName: TextView
        var dueDate: TextView
        var description: TextView
        var mMenus: ImageView

        init {
            taskName = v.findViewById<TextView>(R.id.mTaskName)
            dueDate = v.findViewById<TextView>(R.id.mDate)
            description = v.findViewById<TextView>(R.id.mDescription)
            mMenus = v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener {
                popupMenus(it)
            }
        }

        private fun popupMenus(v: View) {
            val position = mTask[adapterPosition]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editText -> {
                        val v = LayoutInflater.from(c).inflate(R.layout.add_task, null)
                        val name = v.findViewById<EditText>(R.id.taskName)
                        val date = v.findViewById<EditText>(R.id.taskDate)
                        val description = v.findViewById<EditText>(R.id.taskDescription)
                        val df = SimpleDateFormat("dd-MM-yyyy")
                            AlertDialog.Builder(c)
                                .setView(v)
                                .setPositiveButton("Ok") {
                                    dialog,_->
                                    position.name = name.text.toString()
                                    position.dueDate = df.parse(date.toString()) as Date
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
                            .setMessage("Are you sure delete this Information")
                            .setPositiveButton("Yes") { dialog, _ ->
                                mTask.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c, "Deleted this Information", Toast.LENGTH_SHORT)
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
        val v = inflater.inflate(R.layout.list_item,parent, false)
        return TaskViewHolder(v)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val newTask = mTask[position]
        holder.taskName.text = newTask.name
        holder.dueDate.text = newTask.dueDate.toString()
        holder.description.text = newTask.description
    }

    override fun getItemCount(): Int {
        return mTask.size
    }

}