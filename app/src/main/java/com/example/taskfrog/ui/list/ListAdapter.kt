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

class ListAdapter() : RecyclerView.Adapter<ListAdapter.ListViewHolder>()
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
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
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

}