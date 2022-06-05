package com.example.taskfrog.ui.list

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.taskfrog.R
import com.example.taskfrog.room.FrogList
import com.example.taskfrog.room.FrogListViewModel

class ListAdapter(
    val c: Context,
    val listFragment: ListFragment,
    private val onItemClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>()
{

    inner class ListViewHolder(v: View, private val onItemClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var listName: TextView
        var listId: TextView
        var mMenus: ImageView
        var mFrogListViewModel = ViewModelProvider(listFragment).get(FrogListViewModel::class.java)

        init {
            v.setOnClickListener(this)
            listName = v.findViewById<TextView>(R.id.mTitle)
            listId = v.findViewById<TextView>(R.id.list_id)
            mMenus = v.findViewById(R.id.mMenus)
            mMenus.setOnClickListener {
                popupMenus(it)
            }
        }

        private fun popupMenus(v:View) {
            val position = mFrogLists!![adapterPosition]
            val popupMenus = PopupMenu(c, v)
            popupMenus.inflate(R.menu.show_menu_list)
            popupMenus.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editText -> {
                        val v = LayoutInflater.from(c).inflate(R.layout.add_list, null)
                        val name = v.findViewById<EditText>(R.id.listName)
                        AlertDialog.Builder(c)
                            .setView(v)
                            .setPositiveButton("Ok") { dialog, _ ->
                                position.list_name = name.text.toString()
                                mFrogListViewModel.updateFrogList(position)
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
                                mFrogListViewModel.deleteFrogList(position)
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
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item,parent, false)
        return ListViewHolder(v, onItemClicked)
    }

    private var mFrogLists: List<FrogList>? = null
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val current = mFrogLists!![position]
        holder.listName.text = current.list_name
        holder.listId.text = current.frogListId.toString()
    }

    override fun getItemCount(): Int {
        if(mFrogLists == null){
            return 0
        } else {
            return mFrogLists!!.size
        }
    }

    fun setFrogLists(frogLists: List<FrogList>?) {
        mFrogLists = frogLists
        notifyDataSetChanged()
    }

}