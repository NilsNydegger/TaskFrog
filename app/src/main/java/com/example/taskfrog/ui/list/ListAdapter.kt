package com.example.taskfrog.ui.list

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.room.FrogList

class ListAdapter(
    val c: Context,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>()
{
    var listOfFrogLists = emptyList<FrogList>()

    inner class ListViewHolder(
        listView: View
    ): RecyclerView.ViewHolder(listView), View.OnClickListener {

        var listName: TextView
        var mMenus: ImageView

        init {
            listView.setOnClickListener(this)
            listName = listView.findViewById(R.id.mListName)
            mMenus = listView.findViewById(R.id.mMenus)
            mMenus.setOnClickListener {
                popupMenus(it)
            }
        }

        private fun popupMenus(v:View) {
            val position = listOfFrogLists[adapterPosition]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.show_menu_list)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editText -> {
                        val v = LayoutInflater.from(c).inflate(R.layout.add_list, null)
                        val name = v.findViewById<EditText>(R.id.listName)
                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Yeh") { dialog, _ ->
                                position.list_name = name.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(c, "List Name is Edited", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()

                            }
                            .setNegativeButton("Cancel") { dialog, _ ->
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
                            .setMessage("Are you sure delete this List?")
                            .setPositiveButton("Yes") { dialog, _ ->
                                //TODO Add Delete List from DB
                                notifyDataSetChanged()
                                Toast.makeText(c, "Deleted this List", Toast.LENGTH_SHORT)
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

        override fun onClick(v: View) {
            val position = adapterPosition
            onItemClicked(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfFrogLists.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentList = listOfFrogLists[position]
        holder.listName.text = currentList.list_name
    }

    fun setData(frogListList: List<FrogList>){
        this.listOfFrogLists = frogListList
        notifyDataSetChanged()
    }

    fun updateRecyclerView(){

    }

}